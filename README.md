
# 💪 STRAP 

충남대학교 기초 프로젝트 교과목에서 진행한 프로젝트로 지역 내 헬스장에서 그룹원들 간에 대면 운동을 통해 **헬스 입문자**의 지속적인 헬스를 장려하기 위한 **헬스케어 어플리케이션**

## 👩‍👩‍👧‍👦 팀 소개

|Part|Name|
|:-:|:-:|
|Android & Leader|고도현|
|Android|백동훈|
|UI & UX|김재현|
|UI & UX|이주형|

## 프로젝트 배경

오늘날 많은 사람들이 다양한 목표을 가지고 헬스를 시작한다. 하지만 시작하기 전의 마음가짐과 달리 초기 목표를 달성하기 위해 헬스를 꾸준히 수행하는 이들은 보기 드물다. 헬스 경력 3개월 미만의 입문자들을 대상으로 설문 조사를 실시했고 의지가 부족해서, 시간적으로 여유롭지 못해서, 관련 지식이 부족해서 등의 다양한 이유로 헬스를 꾸준히 하기 어렵다는 결과 나왔다. 설문 결과를 분석한 결과 의지도 부족한 상황에서 헬스를 혼자 하니 흥미가 떨어지고 귀찮아져 헬스를 그만두게 된다는 결론이 나왔다. 이러한 문제를 해결하고 **헬스 입문자**들의 지속적인 헬스를 장려하고자 **지역 내 헬스장 기반 그룹 헬스케어 어플리케이션**을 제안한다.

## 프로젝트 내용

본 어플리케이션에서 사용자는 하나의 구체적인 목표를 가진 그룹 커뮤니티에 가입한다. 그룹 커뮤니티는 지역 내의 헬스장을 기반으로 생성과 참여가 가능하며 이 때 그룹이 헬스를 실시할 요일과 시간을 설정해야 한다. 또한 그룹에서 목표로 한 운동 루틴과 그룹원들의 운동 기록을 확인할 수 있고 그룹 운동 루틴에서의 중량과 횟수는 사용자의 운동 능력에 따라 커스텀이 가능하다. 그룹 내 랭킹과 뱃지 시스템을 통해 사용자의 성취감과 경쟁심을 자극하여 헬스에 대한 동기부여를 한다. 본 어플리케이션을 통해 대면 운동을 장려하여 그룹원들이 헬스를 꾸준히 수행할 수 있도록 돕는다.

## 스토리보드


<div align="center">
<image src="https://cdn.discordapp.com/attachments/951723769087541271/960187176178565140/2e0faf9d2a30d706.jpeg" height="900" width="800"/>
</div>

## 프로토타입

[Figma](https://www.figma.com/file/b9UqOfKz3meDP9q1bB0YON/Untitled?node-id=0%3A1)

## 기능

[Notion](https://even-hardboard-7c6.notion.site/bf402c20ef1d40ac9d140640db39ecbe?v=69e09ed806fe49689ee1ea46f7d0a943)

## Todo

프로젝트 설계 당시 MVVM 으로 설계했으나 Leader 를 제외한 팀원 모두가 안드로이드에 입문하는 단계였기에 MVVM 으로 구현하는 것은 무리라고 생각, MVC 에 초점을 맞춰서 구현하였음. 추후에 시간이 된다면 Single Activity 와 MVVM 으로 Migration 하는 것을 목표로 함.

 - [x] 그룹 커뮤니티 생성 및 가입 관련 페이지 Fragment 로 전환 완료
       (지역 내 헬스장 선택, 그룹 커뮤니티 생성, 그룹 커뮤니티 가입)

## 브랜치 관리 방법
feature -> develop -> master 로 관리한다.

### master
'master' 브랜치는 배포 가능한 상태만을 관리한다.  
커밋할 때는 태그를 사용해서 배포 번호를 기록한다.

### develop
'develop' 브랜치는 통합 브랜치 역할을 수행한다.  
이 브랜치를 기반으로 개발을 진행하고 모든 기능이 정상적으로 동작할 수 있는 상태를 유지해야 한다.  
기능이 추가되고 문제가 없는 경우 'master' 브랜치에 merge 한다.

### feature
'feature' 브랜치는 새로운 기능, 버그 수정이 필요할 때 'develop' 브랜치로부터 분기한다.  
개발이 완료되면 'develop' 브랜치에 merge 한다.  
'feature' 브랜치는 다음과 같이 'develop' 브랜치에서 분기한다.

```
$ git checkout -b kodohyeon_feature develop
```

'feature' 브랜치에서 모든 작업이 끝나면 다음과 같이 'develop' 브랜치로 merge하고 더이상 필요하지 않은 'feature' 브랜치는 삭제한다.

```
$ git checkout develop
Switched to branch 'develop'.
$ git merge --no-ff kodohyeon_feature
Updating asd293u...ddu9.
(Summary of changes)
$ git branch -d kodohyeon_feature
Deleted branch kodohyeon_feature.
$ git push origin develop
```
