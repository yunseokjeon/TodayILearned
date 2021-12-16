# 2021년 12월 11일 회고

GitHub에서 커밋 기록을 살펴보니 CaptureTheSequence 프로젝트를 2021년 11월 7일에 시작을 했다. Spring Boot, JPA, React 등을 배우고 이를 적용하면서 익히기 위해서 무엇인가를 만들어 봐야겠다고 느꼈다. 그래서 금융 시장 데이터를 활용해 전략에 대한 최적 베팅 비율을 계산해 내는 서비스를 만들어 보자고 생각했고, 데이터로 부터 기회를 포착해 낸다는 의미에서 'Capture The Sequence'라고 이름을 지었다. 그리고 약 한달 가량이 지났다.

아직 프로젝트가 끝나지 않았지만, 그 짧은 시간 동안에도 몇번 지뢰(?)를 만났고, 기억이 희미해 지기 전에 이를 정리해 보는 것이 좋을 것 같아, 배운 것들을 적어 본다.

## root 이외의 DB 계정을 사용하고 싶어

application.properties에서 DB 사용자를 root가 아닌 다른 계정을 넣으면 연결이 거부되는 현상을 만났다. 분명 새로 만든 계정에 모든 권한을 부여했는데, 왜 그런 것일까? 구글링을 해도 권한을 부여하라는 조언 외에는 찾을 수가 없었다. 분명 권한을 다 부여했는데.... IDE를 통해 내부에서 무슨 일이 일어나는지 하나씩 쫓아다닌 결과 Host에 대한 코드를 발견할 수 있었고, 다시 이에 대해 찾아보니 DB 사용자의 Host에 대해 설정을 할 수 있음을 알게 되었다.  

```
UPDATE mysql.user SET Host='%' WHERE Host='localhost' AND User='username';
FLUSH PRIVILEGES;
```

이렇게 설정하니 문제가 해결되었는데, 다만 이렇게 해도 다른 보안 이슈가 생기지 않는지는 모르겠다. 실무에서는 어떻게 하고 있을까?

## JPA를 사용하다가 만난 예약어 문제

내가 만드는 서비스에서는 회원을 두 가지 그룹으로 나누려 하였고, 책에서 배운 JPA 코드를 참조해 Java의 열거형을 사용하여 코드를 작성하였다. 그런데 Entity를 만들 수 없단다. 나는 분명 문법적 오류를 만든 것 같지 않은데, 왜 안되는 걸까? 별로 작성한 코드가 많지도 않은데, 벌써 문제가 생기니 공황장애가 올 것 같았다. 20일 전 정도의 일이라 기억이 희미하지만, 찾아본 결과 JPA에서 Groups는 사용할 수 없는 것 같았고, 이를 다른 타입으로 변경하여서 해결하였다.

## useFetch

이번에는 프론트엔드에서 문제가 발생했다. 주말 내내 이 문제 때문에 디버깅을 했다. 회원가입 신청을 한 사용자 목록을 불러오는 간단한 작업이었는데, React에서 이 일을 하는 함수를 호출하면 계속해서 함수가 실행되었다. useEffect를 통해 처음 렌더링 될 때만 함수를 실행시키면 되겠지라고 생각했지만, 이번에도 두번 함수가 실행되었다. 함수가 두번 실행되는 것 자체는 큰 문제가 아니었지만, 문제는 하위 컴포넌트에 객체를 전달하여 렌더링하는 부분에서 'undefied 요소여서 접근할 수 없다'는 문제가 발생했다. 시간은 계속 지나는데, 도대체 이유를 알 수 없었다. 그러다가 문제점을 요약하여 동생의 지인에게 조언을 구할 기회가 생겼고, useEffect를 사용하는 방법에 대해 조언을 들을 수 있었다.

```JavaScript
// useFetchUsers.js

const useFetchUsers = () => {
    const [loading, setLoading] = useState(true);
    const [users, setUsers] = useState(null);
    const [error, setError] = useState(null);

    useEffect(() => {
        getAllUserList()
            .then((res) => {
                console.log(res);
                setUsers(res.data);
                setLoading(false);
            })
            .catch((error) => {
                console.log(error);
                setError(error);
                setLoading(false);
            })
    }, []);

    return { loading, users, error };
}

export default useFetchUsers;

// AdminContainer.js
const AdminContainer = () => {

    if (localStorage.getItem('SequenceEmail') !== "yun@hello.com") {
        window.location.href = "/";
    }

    const { loading, users, error } = useFetchUsers();

    if (loading) return <div>Loading...</div>;
    if (error) return <div>Error!</div>;

    return (
        <div>
            {/* {JSON.stringify(users)} */}
            {users.map((user, i) => {
                return <DisplayUser user={user} key={i} />
            })}
        </div>
    )

}

export default AdminContainer;
```

이렇게 개선하고 나니, 문제가 말끔하게 해결되었다.

## 왜 DB에는 값이 들어가지 않는가?

Excel 파일을 전송받아, 데이터를 DB에 넣은 후, 결과를 반환하는 API를 만드는 작업을 하였다. 그런데 Persistence Context에서 관리되는 객체에는 값이 저장되고, 결과도 잘 반환되는데, 정작 DB에 확인을 해보니 값이 들어가지 않는 것이었다. 

```Java

public List<PriceEntity> getPriceEntityList(UserEntity user, MultipartFile file, PriceTableCategory priceTableCategory)
            throws IOException {
        List<PriceEntity> priceEntityList = new ArrayList<>();

        String extension = FilenameUtils.getExtension(file.getOriginalFilename());
        if (!extension.equals("xlsx") && !extension.equals("xls")) {
            throw new IOException("You only have to upload a Excel file.");
        }

        Workbook workbook = null;

        if (extension.equals("xlsx")) {
            workbook = new XSSFWorkbook(file.getInputStream());
        } else if (extension.equals("xls")) {
            workbook = new HSSFWorkbook(file.getInputStream());
        }

        try {
            Sheet worksheet = workbook.getSheetAt(0);

            for (int i = 1; i < worksheet.getPhysicalNumberOfRows(); i++) {
                Row row = worksheet.getRow(i);
                PriceEntity priceEntity = PriceEntity.builder()
                        .marketDate(row.getCell(0).getDateCellValue()
                                .toInstant().atZone(ZoneId.systemDefault()).toLocalDate())
                        .itemName(row.getCell(1).toString())
                        .startingPrice(row.getCell(2).getNumericCellValue())
                        .closingPrice(row.getCell(3).getNumericCellValue())
                        .user(user)
                        .build();
                priceEntityList.add(priceEntity);
            }

        } catch (Exception e) {
            throw e;
        }

        deleteDuplicatePriceItem(user, priceEntityList.get(0), priceTableCategory);
        if (priceTableCategory == PriceTableCategory.STOCK) {
            user.getStockPriceList().addAll(priceEntityList);
        } else if (priceTableCategory == PriceTableCategory.FUTURES) {
            user.getFuturesPriceList().addAll(priceEntityList);
        }

        entityManager.flush();
        entityManager.clear();

        log.info("================================================");
        log.info(user.getStockPriceList().get(0).getItemName());
        log.info(String.valueOf(user.getStockPriceList().size()));
        log.info("================================================");
        return priceEntityList;

    }

```
로그를 통해 영속성 컨텍스트가 관리하는 객체에 변화가 있음을 확인했고, 이를 DB에 반영하기 위해 entityManager.flush();도 실행하였는데, 정작 SQL은 실행되는 것이 없었다. 더 난감한 것은 그렇다고 에러가 발생하지도 않는다는 것이었다.

결국 새로 Repository를 만들고, 아래와 같이 코드를 고친 결과 해결이 되었다.

```Java
        priceRepository.deleteDuplicatePriceItem(priceEntityList.get(0).getItemName(), user);
        priceRepository.findAllByUser(user).addAll(priceEntityList);
        priceRepository.saveAll(priceEntityList);
```

연결된 객체여서 자연히 DB에도 반영될 것이라 생각했는데, 아무래도 이 부분이 내 생각과 다르게 동작한 것 같았다.


## Postman에서는 작동이 되는 API인데...

어제 해결한 문제인데, Postman으로 파일을 전송하면 작동하는 API인데, React에서 파일을 전송하면 서버에서 인증이 안된 유저라며 접근이 거부됐다. Axios를 처음 사용하다보니 헤매게 된 것인데, 시행착오 끝에 다음과 같이 해결되었다.

```JavaScript
export async function sendExcel(formData) {

    const accessToken = localStorage.getItem("ACCESS_TOKEN");

    const headers = {
        'Content-Type': "multipart/form-data",
        'Authorization': 'Bearer ' + accessToken
    };

    let result = await axios.post(API_BASE_URL + "/file/excel/read", formData, {headers: headers}).then(res => {
        return res.data.data;
    });

    return result;

}
```

## 그래도 심심하지 않아서 좋다.

프로그래밍을 하다보면 답답함을 느낄 때가 자주 있다. 이렇게 스트레스 받다가 공황장애로 죽지 않을까 싶을 때도 있다. 그런데 문제가 하나씩 해결되고, 한 걸음씩 나갈 때마다 기쁘고, 무엇보다 개발을 하는 시간동안 매우 집중을 하면서 시간이 금방 지나간다. 심심하지 않다는건데, 난 스트레스를 받아도 심심하지 않은 게 좋다. 미래에 심심하지 않으면서 비지니스에 영향력을 갖는 결과까지 본다면 더 기쁘지 않을까?


