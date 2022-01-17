OS를 간단하게 요약하면 **하드웨어를 관리하는 프로그램**이다. 응용 프로그램에게는 메모리 관리 의무가 없으므로, 하드웨어 관리는 OS의 몫이다. 

## Computer Operations

### Start up
- Bootstrap program : OS의 구동을 돕는 프로그램. ROM에 저장됨.
- System daemons : 커널 백그라운드에서 계속 구동되며, init 프로세스가 다른 daemon들을 구동함.

### Interrupt
- CPU에게 interrupt handler를 실행하라고 보내는 이벤트.
    - interrupt handler : 함수 하나
    - interrupt vector : interrupt handler 주소들의 배열
  
# Process

Process : program in execution

Virtual Memory : 각 프로세스는 일정한 메모리 공간(logical space, fake space)을 가진다고 내부적으로 간주한다. (32bit의 경우 4GB)

## Process State

- New
  - 프로세스가 처음 생성되었을 때
- Ready
  - 프로세스가 프로세서에게 할당되기를 기다리고 있을 때
  - Ready Queue에 있다.
- Running
  - 프로세스가 프로세서에 할당되어 실행되고 있을 때
  - 각 코어는 한 번에 하나의 프로세스만 실행한다.
- Waiting
  - 프로세스가 실행 중 작업을 완료하기 위해 특정 이벤트를 기다리고 있을 때
  - Wait Queue에 있다.
- Terminated
  - 프로세스가 실행 중 종료되었을 때



