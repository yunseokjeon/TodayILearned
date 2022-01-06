동료들에게 Git 사용과 관련된 문제에 대해 비슷한 질문을 많이 받는 것 같습니다. Q&A 들을 간단히 정리해 둡니다.

※ 저희팀은 IntelliJ 기반 Git GUI를 사용합니다. 그래서 git command 대신, IntelliJ 메뉴를 설명합니다.

오전 9:06 · 2022년 1월 6일·Twitter Web App

https://twitter.com/joosings/status/1478880586123350016

Q : A소스 코드 N라인 누가 마지막에 수정한거지?

A : Git 메뉴 > Current File > Annotate with Git Blame

<img src="https://pbs.twimg.com/media/FIXoIO2aUAA7z6h?format=jpg&name=360x360">

https://twitter.com/joosings/status/1478880587566174208

Q : 충돌이 왜 나는 거야?

A : Git은 소스코드 라인을 최소 단위로 변경을 관리하리해. 그래서 동일한 라인을 두명이 동시에 수정하고 저장소에 커밋하면 Git은 누가 수정한 것을 최종버전으로 할지 결정할 수 없어. 그래서 이건 사람이 정하라고 말해주는 거야!

오전 9:06 · 2022년 1월 6일·Twitter Web App

https://twitter.com/joosings/status/1478880589243875328

Q : 그럼 A=B 라는 라인에서 한 사람은 A를 다른 사람은 B를 수정해도 충돌이 나겠네?

A : 맞아! 이런 경우에는 A와 B의 코드를 합해서 한 라인(A1 = B1)을 만들어 줘야해. 만약 두 사람이 동시에 A를 수정했다면 누구의 것이 맞는지 선택해서 A1 or A2 = B 라인을 만들어 줘야 해!

오전 9:06 · 2022년 1월 6일·Twitter Web App

https://twitter.com/joosings/status/1478880590816739331

Q : 그래서 충돌은 어떻게 해결해? 

A : 충돌 시, 머지 메뉴를 선택하면 에디터가 뜨는데 왼쪽(나의 작업 파일), 오른쪽(저장소 파일), 가운데가 충돌을 해결해줄 파일이야. 그림과 같은 순서로 해결할 수 있어.

<img src="https://pbs.twimg.com/media/FIXzZR4aUAIH1wz?format=jpg&name=360x360">

<img src="https://pbs.twimg.com/media/FIXzbiUaUAIm-rB?format=jpg&name=360x360">

<img src="https://pbs.twimg.com/media/FIXz-PFaUAEjFwu?format=jpg&name=360x360">

<img src="https://pbs.twimg.com/media/FIX04xraQAI8Obc?format=png&name=360x360">

https://twitter.com/joosings/status/1478880596198047745

Q : 지금 뭔가 잘못 코딩하고 있는 것 같아. 직전 커밋 상태로 깨끗하게 돌리고 싶어. 

A : 커밋하지 않은 변경사항은 Commit 창 > 파일 선택 (복수개 가능) > 우클릭 > Rollback 을 통해 이전 커밋 상태로 돌릴 수 있어

<img src="https://pbs.twimg.com/media/FIX5fPFaQAMNaWq?format=png&name=900x900">

https://twitter.com/joosings/status/1478880601566740482

Q : 특정 커밋의 변경사항을 되돌려야 하는데, 다음에 다시 사용하게 이력은 남기고 싶어 

A : Git 창 > History 에서 커밋 선택 (복수개 가능) > 우클릭 > Revert Commit

<img src="https://pbs.twimg.com/media/FIYI6j9aAAEmrX-?format=jpg&name=900x900">

https://twitter.com/joosings/status/1478880603902980100

Q : 뭔가 완전히 잘못되었어! 브랜치를 특정 커밋 지점으로 완전히 돌아가고 싶어! 커밋된 변경도 다 삭제하고 싶어!

A :  Git 창 > History 에서 특정 커밋 선택 > 우클릭 > Reset Current Branch to Here > Hard Reset 옵션 선택
( ※ 선택한 커밋 이후의 변경이 모두 정말 삭제됨으로 주의! )

오전 9:06 · 2022년 1월 6일·Twitter Web App

https://twitter.com/joosings/status/1478880605664575490

Q : 브랜치를 특정 커밋 지점으로 돌리면서, 해당 커밋 이후에 발생한 변경 사항들은 워킹 디렉토리에 유지하고 싶어. 

A : Git 창 > History 에서 특정 커밋 선택 > 우클릭 >  Reset Current Branch to Here > Soft Reset 옵션 선택

오전 9:06 · 2022년 1월 6일·Twitter Web App

https://twitter.com/joosings/status/1478880607157768195

Q : Reset 후 원격 저장소에 Push 하면 Push Reject 되네. Merge나 Rebase 하면 자꾸 Reset한 걸 다시 가져와... 내걸로 원격 저장소를 덮어쓰고 싶은데

(이어서)

https://twitter.com/joosings/status/1478880608734818306

A : 이럴때는 Push Force 메뉴를 선택해야 해. 근데 master 브랜치는 디폴트 설정으로 Force Push가 막혀있어. 위험하니까! 설정을 바꾸면 가능하지만, 혼자 개발하는 경우가 아니라면 관리자와 상의해야 해.

<img src="https://pbs.twimg.com/media/FIYAZsVagAIQRG4?format=png&name=small">

<img src="https://pbs.twimg.com/media/FIYAia1aMAAXVy0?format=jpg&name=360x360">

<img src="https://pbs.twimg.com/media/FIYAcTgagAE98SU?format=png&name=360x360">

<img src="https://pbs.twimg.com/media/FIYAkpIaUAAUx9_?format=jpg&name=360x360">

https://twitter.com/joosings/status/1478880613386317827


Q : 특정 커밋에서 발생한 변경사항을 다른 브랜치로 옮기고 싶어

A : 
1) Git 창 > History 에서 커밋 선택 (복수개 가능) > 우클릭 >  Create Patch > File > 확인 (저장)  
2) 옮길 브랜치로 체크아웃
3) Git 메뉴 > Patch > Apply patch 선택

오전 9:06 · 2022년 1월 6일·Twitter Web App

https://twitter.com/joosings/status/1478880616091643913

Q : 지금 개발중이라 빌드가 안되는 상태인데, 잠시 다른 브랜치로 이동해서 작업하고 싶은데, 이걸 커밋해 두기는 애매하네 어떻하지? 

A : (임시 저장하기) Git 메뉴 > Uncommitted Changes > Stash Changes 
(다시 가져오기) Git 메뉴 > Uncommitted Changes > Unstash Changes > Apply Stash

오전 9:06 · 2022년 1월 6일·Twitter Web App

https://twitter.com/joosings/status/1478880617584820224

Q : 브랜치 옆에 표시되는 이 화살표는 뭐야? 

A : 아래로 오는 화살표는 한 마디로 원격 저장소에 최신 커밋이 생겨서 당겨올(Pull) 변경사항이 있다는 뜻이다. 위로 가는 화살표는 로컬 저장소에 커밋이 생겨어 원격 저장소에 Push 할 커밋이 있다는 뜻이야!

<img src="https://pbs.twimg.com/media/FIXxLNHaMAUFJLp?format=jpg&name=120x120">

https://twitter.com/joosings/status/1478880619157663744

