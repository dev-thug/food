# 1. 주제
요리학원에서 수강생의 온라인 실습 신청을 위한 시스템 입니다.  
수강생은 개설된 강좌에 수강 신청후 실습비를 입금하면 수강 신청 완료가 됩니다.   
강사는 강의를 개설하고 수강생들의 수강신청을 받습니다.  


# 2. REST-API 문서
- [Swagger 문서](https://hayagou.shop/api/swagger-ui.html)

# 3. 사용한 OPEN-API

- 식단과 식재료에대한 데이터들은 공공데이터 API를 호출하여 필요한 데이터만 가공후 디비에 저장후 사용
- [조리식품의 레시피 DB](http://www.foodsafetykorea.go.kr/api/openApiInfo.do?menu_grp=MENU_GRP31&menu_no=661&show_cnt=10&start_idx=1&svc_no=COOKRCP01)

# 4. ERD

|기존ERD|새로디자인한 ERD|
|--|--|
|![oldERD](https://sat02pap001files.storage.live.com/y4mG25ylLZD0cGttzcGU-QoELKceuPiLL3TH8lTrIW81QWdEk6oKPa-WiO847KMKF_qQoKelvFs7axXSDPiJ24U4wqkpXnTZLoAjkAws48iNsnt6cgp8RieedOAWOsnapaCjVLzgrKIXZPiD1O7LMlllzkbMHuCHVg4WwTRZot3BlL3MFyNcHunl-rwvTaCH8nJPNVl5xn26HHqmGkAscIqWw/%EA%B8%B0%EC%A1%B4ERD.png?psid=1&width=1189&height=925)|![NewERD](https://sat02pap001files.storage.live.com/y4m15FIek_Zp48BugGZVMDnD4NCCWsm286uydVGHoBm37qbFNaVoqRzW_qMiy2s6Qroa0aE2eThKi2NSzV4FnNnDwB7keTSgINAwYXCaw_oitl4lu_Ey67859eru48Kpkr4xRKwdXcO_XgpuG_EKnHVIH88bXmfAeSP-Ma84EAqGgP4--w6e62zWEBSrSWOaKhZ8_fF9O9IHuOZ7MFUocyuIg/%EC%83%88%EB%A1%9C%EB%94%94%EC%9E%90%EC%9D%B8%ED%95%9CERD.png?psid=1&width=1422&height=652)|

- 레시피와 식재료 테이블을 식단으로 반정규화
- Spring Security를 적용하면서 강사, 수강생 테이블 삭제후 사용자로 통합
- 신청내역 테이블 삭제후 신청 테이블에서 쿼리문으로 조회
- 식재료_신청내역 테이블 삭제후 비지니스로직에서 입금이 완료된 수강생을 수와 식단을 계산하여 신청내역 조회

# 5.개발 도구
## Spring boot
- JAVA 11
- JWT
- Spring Boot Security
- Spring Data JPA
- Spring HATEOAS

## Build Tool
- Gradle

## Database
- Oracle11 xe
- H2
- Redis
- OPEN-API

## 배포 서버
- [OracleCloud](https://www.oracle.com/kr/cloud/)
- Ubuntu20
 
# 기존 DB 설계 자료
![db0](https://sat02pap001files.storage.live.com/y4mX2XFBA_QNpltoCWsOvCFZLGj2sDtHTh_6VKk77XobU4BlFzs0NGwB8SgNLcJ_PpplNWfT-VeecMZCxvCEiG8liKQ7hWV0kFVmZNM41lzzA5XN9Et9oKOjU6NToLRyMdQ4ZT5V8uL9hCAj8Am83ttN8B0oi_4UGQsFNMyMxRd8MPECTbNPlN7W4Bxg0fGMRukQSxcaax_yxhmH7aImdhP3Q/db0.jpg?psid=1&width=794&height=1123)
![db1](https://sat02pap001files.storage.live.com/y4mNazF8b_82WB93tOkJzk46Sdb3VWL3VR21isssCYIpv9ULbUQfNM59m6DdwHDQ58LXBal1fxGI-U6K-Xjov7_dBFIN4QELH_3h4Ty-Xb6Nf83yJ45S0siSOnD6BYk-ii20ReUKk3L-CaKF7pux50VmtpaL9nFc4ZbS359TbQA_z3UocPVTjiVw9u7xn3YR_I3Mt5zDzEMOhuuMnY-1kP15g/db1.jpg?psid=1&width=794&height=1123)
![db2](https://sat02pap001files.storage.live.com/y4mfc9hocYDn6vQX5Aaq7nn8l7SGIwYV-N54Csf1q1yvUFcLQ2_O7oiJNnPOWKBuit1sjPIVM85urzz2FFEDmxCEP1MuBcveG9aALe0LaqWqgqajeqx7ojAEtZKHPjVhkKCMuf_L9QuMWxozeKRFLI79bAP1uCVKWoC32kRXnEXyVOWAWvtLSlwdyl08IZ3BeJdP1xuZfIIR0AeIs7p0s6T8w/db2.jpg?psid=1&width=794&height=1123)