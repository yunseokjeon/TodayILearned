## SYN & ACK
- Synchronize Sequence Number
- Acknowledgement

TCP header에는 Code Bit이라는 부분이 존재. (6bit로 구성되어 있으며, Urg - Ack - Psh - Rst - Syn - Fin 순서.)

SYN 패킷일 경우 000010 이고, ACK 패킷일 경우 010000 이 된다.

## 연결 성립

클라이언트가 서버에 SYN(a) 패킷을 보내서 접속을 요청.

서버는 클라이언트에 ACK(a+1)과 SYN(b) 패킷을 전송해서 요청을 수락

클라이언트가 다시 ACK(b+1)을 보내면 연결이 성립.

## 연결 해제

클라이언트가 연결 종료를 위한 FIN 플래그 전송.

서버는 ACK 응답. 데이터를 모두 보내는 동안 대기. 데이터를 보낸 후에 클라이언트에게 FIN 플래그 전송.

클라이언트는 FIN을 받았다는 응답으로 ACK 전송.

ACK를 받은 서버는 소켓 연결을 닫는다.

클라이언트는 혹시 있을 수 있는 잉여 패킷을 위해 세션을 잠시 열어 놓는다.



