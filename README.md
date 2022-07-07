## 마일리지 지급 서비스

### 1. 프로젝트 환경
- Spring Boot
- Spring Data JPA
- JAVA 11
- Lombok
- H2(Database)

### 2. 테이블 설계
![image](https://user-images.githubusercontent.com/22049906/177768907-1088f0b5-0e23-4b23-b606-3b1e24fa676b.png)
  

### 3. API 목록
|Method|URL|Description|
|------|---|---|
|POST|/events|리뷰 작성, 수정, 삭제 (action: ADD, DELETE, MOD)|
|GET|	/point/{user_id}|	사용자 포인트 이력 및 총 보유 포인트 조회|

### 4. 실행 방법
- 사용자 등록
회원이 가입되어있다는 가정하에 진행되어야 하므로 사용자 추가를 한다.  
insert into user(user_id, user_nm, reg_dtime, upt_dtime)  
values('3ede0ef2-92b7-4817-a5f3-0c575361f745','정민지', now(), now());

  
- 리뷰등록  
![image](https://user-images.githubusercontent.com/22049906/177761046-c42dcb38-cf01-4198-9cb1-8360d6de2248.png)
  
    
- 리뷰수정  
![image](https://user-images.githubusercontent.com/22049906/177761177-29a58eb4-f868-4753-af2f-a77e9ce023e9.png)
  
    
- 리뷰삭제  
리뷰 삭제시 해당 리뷰에 등록된 이미지도 함께 삭제한다.
![image](https://user-images.githubusercontent.com/22049906/177761273-55607a18-8b8f-4067-855e-559795c651cd.png)
