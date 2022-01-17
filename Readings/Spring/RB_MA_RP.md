### @RequestParam
- 한 개의 http 요청 패러미터를 받기 위해서 사용.
- 필수적으로 해당 패러미터가 전성되어야 함. 전송되지 않으면 400 에러 발생.
- required를 false로 설정할 수 있으며, defaultValue 옵션을 사용할 수도 있음.

### @RequestBody
- 클라이언트가 전송하는 JSON(application/json) 형태의 http body 내용을 자바 객체로 변환시켜주는 역할. 
- body가 존재하지 않는 http Get 메소드에 적용하려 하면 에러 발생.

### @ModelAndAttribute
- 클라이언트가 전송하는 multipart/form-data 형태의  http body 내용과 http 패러미터 값들을 생성자나 Setter를 통해 주입하기 위해 사용됨.
- @ModelAndAttribute는 바인딩을 위한 생성자나 Setter가 없으면 매핑이 되지 않지만, @RequestBody는 그렇지 않음.