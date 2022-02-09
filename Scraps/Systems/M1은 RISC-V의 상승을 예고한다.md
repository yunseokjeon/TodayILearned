M1은 RISC-V의 상승을 예고한다

"M1은 패러다임 이동의 시작으로 RISC-V에 도움이 되겠지만, 당신이 생각하는 그 방식은 아닐 것이다"
"M1 칩은 왜 그렇게 빠를까?" 를 쓴 엔지니어의 후속 글. RISC-V의 미래를 재미난 관점으로 예상.

M1 성능의 요인은
1. 많은 수의 디코더와 OoO 비순차 실행
2. GPU,NPU,DSP 등 여러개의 전용칩

이 글은 2번 Heterogeneous(이기종) 컴퓨팅에 대한 더 자세한 내용
전용칩들은 여러가지로 부를 수 있는데 여기선 모두 Coprocessor(보조프로세서) 로 통칭(혹은 Accelerator 라고도 부를수 있음)
- 코프로세서는 완전히 새로운 트렌드는 아님
- 1985년에 나온 Amiga 1000도 오디오/그래픽을 위한 보조프로세서가 있었고, GPU도 보조프로세서이고,
ㅤ구글의 TPU(Tensor Processing Unit) 역시 머신러닝에 최적화된 보조프로세서

[ Coprocessor 는 무엇인가 ]
- CPU와 달리 혼자 살수 없음. 보조프로세서만 넣는다고 컴퓨터가 되지 않으며, 단순히 특정 작업을 잘 하는 특수 목적 프로세서
- 초기의 예시는 인텔의 8087 Floating Point Unit(FPU). 인텔의 8086은 정수 계산은 잘했지만, 부동소수점 연산은 잘 못했음
- 정수 계산으로도 부동소수점 연산을 에뮬레이트 할수 있지만 느렸음. 이건 마치 초기의 마이크로프로세서가 덧셈/뺄셈만 할수 있고 곱셈은 못해서 여러번의 덧셈을 반복해서 곱셈을 처리한 것과 비슷.
- 즉, "복잡한 수학계산은 간단한 것을 반복함으로서 처리가 가능"
- 모든 보조프로세서가 하는 일은 이것과 마찬가지. CPU가 보조프로세서가 하는 일을 할수는 있음. 단순 동작을 반복하면 됨
- 초기에 GPU가 필요했던 이유는 수백만개의 폴리곤/픽셀에 동일한 계산을 반복하는게 CPU에선 시간이 많이 필요하기 때문

[ 데이터는 보조프로세서에서 어떻게 In/Out 하는가 ]
- 마우스/키보드/스크린을 비롯하여 GPU/FPU/Neural Engine을 포함한 모든 보조프로세서는 특정 메모리 에 접근하여 데이터를 읽고 쓰는 것과 같음
- 이 작업들은 Device Driver가 처리하므로 일반 소프트웨어 개발자들은 볼일이 없음
ㅤ→ DMA(Direct Memory Access) 컨트롤러 등이 하는 일
- DOS시절 C/C++ 에서는 포인터로 비디오 메모리 주소에 직접 접근하여 픽셀을 바꾸는게 가능했음
- 보조프로세서는 이런방식으로 동작하여, NPU,GPU,T1 등이 각자 자신들과 통신하는 주소를 가지고 있고, 비동기적으로 통신이 가능
- CPU는 Neural Engine 또는 GPU로 보낼 전체 명령을 메모리에 나열한뒤 그 주소를 Neural Engine/GPU 에 알림
- CPU가 코프로세서가 그 명령과 데이터를 처리할동안 기다릴 필요는 없으니 이때 인터럽트가 필요해 짐

[ 인터럽트의 동작 방식 ]
- 그래픽/네트워크 카드는 PC에 꼽히고 지정된 인터럽트 라인이 있음
- 이건 CPU와 직접 연결된 라인처럼 동작해서, 활성화 되면 CPU가 다른일을 내려놓고 인터럽트를 처리
- 실제로는 현재 위치와 레지스터를 메모리에 저장해서 나중에 돌아갈수 있음
- 그 다음엔 인터럽트 테이블에서 수행할 작업을 찾음. 테이블에 인터럽트 트리거시 실행할 프로그램 주소가 들어있음
- 프로그래머한테는 이런게 보이지 않고, 특정 이벤트에 등록하는 콜백함수처럼 보임. 디바이스 드라이버가 저수준에서 이런 처리를 함
- 이런 설명을 하는 이유는 보조프로세서를 사용할 때 무슨일이 일어 나는지를 알고 있어야 실제 통신할때 어떤 일이 수반되는지를 알수 있기 때문
- 인터럽트를 사용하면 많은 일이 병렬로 일어남.
ㅤ→ CPU가 마우스에 의해 중단되는 동안 응용프그램은 네트워크카드에서 이미지를 가져올수 있고, 마우스가 옮겨지고 나면 CPU는 새 좌표를 얻고, 이걸 GPU로 보내서 새 위치에 마우스 커서를 그림. GPU가 마우스 커서를 그릴때 CPU는 네트워크에서 가져온 이미지처리를 시작
- 이런 인터럽트를 사용해서 M1의 뉴럴엔진에 복잡한 기계 학습작업을 보내서 WebCam에서 얼굴을 식별할수 있음. 뉴럴엔진이 이미지데이터를 처리하기 때문에 이때 컴퓨터와 CPU는 다른 작업을 하면서 사용자에게 반응이 가능

[ The Rise of RISC-V ]
- 2010년 UC 버클리의 병렬컴퓨팅 랩은 보조프로세서를 더 많이 사용하는 방향으로 발전.
- 범용 CPU코어를 쥐어짜서는 더 이상 쉽게 성능을 늘릴수 없다는 것에서 무어법칙의 끝을 보았음
ㅤ→ 특수한 하드웨어인 보조프로세서가 필요해짐
- 클럭주파수는 열과 전력소비등 때문에 쉽게 증가시킬수 없음.
ㅤ→ 많은 디코더와 OoO 비순차 실행을 하는게 하나의 방법
ㅤ→ "M1 칩은 왜 그렇게 빠를까?" 글을 참고 https://news.hada.io/topic?id=3315

[ 트랜지스터 예산을 CPU 코어에 쓸것인가 Coprocessor에 쓸 것인가 ]
- 128코어로 늘린다고 데스크탑 시스템이 더 효율적이 되지 않음
- 80년대 초반엔 2만개의 트랜지스터 예산이 있을때 15000개를 들여서 CPU를 만들면 되었음
- CPU가 100개의 다른 작업을 한다고 할때, 그중 한개의 작업을 처리하기 위한 보조프로세서를 만들면 1000개의 트랜지스터가 필요하면, 모든 작업에 대한 보조프로세서를 다 만들면 10만개의 트랜지스터가 필요해서 예산을 초과해 버림

[ 트랜지스터가 많아지면서 전략이 변경됨 ]
- 초기설계에서는 범용 컴퓨팅에 집중해야 했지만, 요즘은 엄청나게 많은 트랜지스터들이 들어가게 되므로, 그것들로 뭘 해야할지를 알 수 없음
- 그래서 보조프로세서를 설계하는 것이 큰 일이 됨. 다양한 새 보조프로세서를 만드는 많은 연구가 진행.
- 이 연구는 멍청한 가속기상태에서 기초부터 키워야하는 경우들이 많음
- CPU와 달리 모든 단계의 명령들을 읽고 처리하는게 아니기 때문에, 메모리에 접근하거나 정리하는 방법등을 모름
- 이에 대한 해결책은 간단한 CPU를 컨트롤러로 사용하는 것
- 즉, 전체 보조프로세서들은 간단한 CPU에 의해 제어되는 특수한 가속기 회로로서 특정 작업을 가속하도록 구성됨
ㅤ→ 예를 들어 Neural Engine/Tensor Processing Unit 같은 칩은 행렬을 저장할수 있는 큰 레지스터를 조작할 수 있음

[ RISC-V 는 Accelerator 를 제어하도록 맞춤 제작 되었음 ]
- 이게 RISC-V 가 설계된 목적
- 일반적인 CPU작업을 위한 40~50개의 최소 명령어 세트를 가지고 있음
ㅤ→ x86 CPU는 1500개의 명령어 셋이 있음
- 큰 고정 명령어 세트 대신, RISC-V는 확장 개념을 중심으로 설계됨
- 모든 보조프로세서는 다르므로, 따라서 RISC-V는 코어 명령어 세트와 보조프로세서가 필요로 하는 확장명령어 세트를 가지게 구성이 가능

이게 이 글에서 설명하고자 하는 것

- Apple의 M1은 업계 전체가 보조프로세서가 지배하는 미래로 향하게 할 것
- 그리고 이 보조프로세서를 만들기 위해 "RISC-V는 퍼즐의 중요한 부분"이 될 것

[ RISC-V로 Coprocessor를 만들면 좋은 점 ]
- 칩을 만드는 것은 복잡하고 비용이 많이 드는 일
- 칩 검증을 위한 도구 구축부터, 테스트 프로그램을 실행하고, 진단 및 기타 여러가지를 하려면 많은 노력이 많이 필요.
- 이게 요즘 ARM을 사용하는 가치의 일부. 큰 에코시스템이 있기때문에 디자인을 검증하고 테스트가 가능

- 그래서, 자신만의 명령어셋을 가지는 것은 좋은 생각이 아님
- RISC-V에는 여러회사에서 도구를 만들수 있는 표준이 있고, 에코시스템이 생겨서 여러 회사가 부담을 공유할수 있음

- 이미 있는 ARM을 사용하지 않는 이유는? ARM은 범용 목적 CPU로 만들어 져서, 큰 고정 명령어세트를 가짐
- 고객의 요청과 RISC-V 와의 경쟁때문에 ARM도 2019년에 확장용 명령어 세트를 공개했음
- 하지만 여전한 문제는 처음부터 이를 위해 설계된것이 아니라는 것
ㅤ→ 전체 ARM 툴체인은 대형 ARM 명령어 세트를 구현했다고 가정
ㅤ→ 하지만 보조프로세서는 큰 명령집합을 원하거나 필요로 하지 않음
ㅤ→ 보조프로세서는 확장기능이 있는 최소 고정 기본 명령어 세트라는 아이디어 기반으로 구축된 도구의 에코시스템을 원함

- 이게 왜 유익한지는 Nvidia 의 RISC-V 사용에서 인사이트를 얻을수 있음
ㅤ→ 대형 GPU는 컨트롤러로 사용할 일종의 범용 CPU를 필요로 함
ㅤ→ FALCON : FAst Logic CONtroller 라는 칩을 만들어서 사용
ㅤ→ 저비용 고효율

- RISC-V는 작고 간단한 명령어 세트를 가지고 있기때문에 ARM을 비롯한 모든 경쟁제품을 능가
- Nvidia는 RISC-V 를 선택함으로서 더 작은칩을 최소화된 전력으로 가능하게 만듦
- 확장 메커니즘을 사용하면 필요한 작업에 맞는 명령만 추가가 가능

[ ARM은 새로운 x86이 될 것 ]
- 아이러니컬 하게도 Mac 과 PC 가 ARM으로 구동되는 미래를 볼수 있을 것
- 그러나 그 주변의 커스텀 하드웨어들은 RISC-V로 지배된 보조프로세서들이 차지할 것
- 보조프로세서가 대중화 되면서, SoC 위에는 ARM보다 RISC-V 칩들이 더 많아 질 것
- 미래는 ARM or RISC-V 가 아니라, ARM and RISC-V 가 될 것

[ ARM 은 RISC-V 보조프로세서 군단을 지휘하게 될 것 ]
- 범용 ARM 프로세서는 그래픽,암호화,비디오 압축,머신 러닝,신호처리를 담당할 RISC-V 보조프로세서 군대와 함께 중심에 있게 될 것
- UC Berkeley 의 David Patterson 교수와 그의 팀은 이러한 미래가 다가오는 것을 보고, RISC-V 를 그거에 잘 맞게 조정 했음
- 모든 종류의 특수 하드웨어 및 마이크로 컨트롤러들이 RISC-V에 큰 관심을 보이고 있으며, 오늘날 ARM이 지배하는 많은 영역이 RISC-V가 될 것

[ RISC-V를 메인 CPU로 사용하면 안될까 ? ]
- 많은 사람들이 ARM을 RISC-V 로 완전히 교체하는 것은 어떤가함
- 혹자는 RISC-V의 너무도 간단한 명령어 셋이 ARM과 x86이 주는 고성능을 제공 못할 것이라고 함

- 하지만 RISC-V를 충분히 메인프로세서로 사용 가능하고, 성능은 문제가 되지 않음
ㅤ→ 다만, ARM 처럼 고성능 RISC-V를 만들사람이 필요함
ㅤ→ 즉, 가능은 하지만 모멘텀이의 문제라는 것. MacOS와 Windows가 이미 ARM에서 실행이 되고 있음
ㅤ→ 단기적으로 MS나 Apple이 또 다른 하드웨어 전환을 위한 노력을 하지는 않을 것임

https://news.hada.io/topic?id=3447

https://erik-engheim.medium.com/apple-m1-foreshadows-risc-v-dd63a62b2562

```C++
// RISC-V emulator (RV32I only) in one C++ file
// https://gist.github.com/FrankBuss/c974e59826d33e21d7cad54491ab50e8

/*
See https://gitlab.com/nedopc/npc5/blob/master/emu-rv32i.c for the latest version, with more features and less bugs :-)
RISCV emulator for the RV32I architecture
based on TinyEMU by Fabrice Bellard, see https://bellard.org/tinyemu/
stripped down for RV32I only, all "gotos" removed, and fixed some bugs for the compliance test
by Frank Buss, 2018
Requires libelf-dev:
sudo apt-get install libelf-dev
Compile it like this:
g++ -O3 -Wall -lelf emulator.cpp -o emulator
It is compatible to Spike for the command line arguments, which means you can run
the compliance test from https://github.com/riscv/riscv-compliance like this:
make RISCV_TARGET=spike RISCV_DEVICE=rv32i TARGET_SIM=/full/path/emulator variant
It is also compatible with qemu32, as it is used for Zephyr. You can compile the
Zephyr examples for qemu like this:
cd zephyr
source zephyr-env.sh
cd samples/synchronization
mkdir build && cd build
cmake -GNinja -DBOARD=qemu_riscv32 ..
ninja
After this you can run it with the emulator like this:
/full/path/emulator zephyr/zephyr.elf
original copyright:
*/

/*
 * RISCV emulator
 *
 * Copyright (c) 2016 Fabrice Bellard
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL
 * THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

#define XLEN 32

#include <stdio.h>
#include <sys/types.h>
#include <sys/stat.h>
#include <fcntl.h>
#include <stdio.h>
#include <stdlib.h>
#include <stdint.h>
#include <string.h>
#include <unistd.h>
#include <time.h>
#include <libelf.h>
#include <gelf.h>

// uncomment this for an instruction trace and other debug outputs
//#define DEBUG_OUTPUT

// memory mapped registers
#define MTIME_ADDR 0x40000000
#define MTIMECMP_ADDR 0x40000008
#define UART_TX_ADDR 0x40002000

// emulator RAM
#define RAM_SIZE 0x10000
uint8_t ram[RAM_SIZE];

// special memory mapped registers
uint64_t mtime;
uint64_t mtimecmp;

// virtual start address for index 0 in the ram array
uint32_t ram_start;

// used when called from the compliance tests
uint32_t begin_signature = 0;
uint32_t end_signature = 0;

// is set to false to exit the emulator
bool machine_running = true;

// privilege levels
#define PRV_U 0
#define PRV_S 1
#define PRV_H 2
#define PRV_M 3

// CPU state
uint32_t pc;
uint32_t next_pc;
uint32_t insn;
uint32_t reg[32];

uint8_t priv = PRV_M; /* see PRV_x */
uint8_t fs; /* MSTATUS_FS value */
uint8_t mxl; /* MXL field in MISA register */

uint64_t insn_counter;
int pending_exception; /* used during MMU exception handling */
uint32_t pending_tval;

/* CSRs */
uint32_t mstatus;
uint32_t mtvec;
uint32_t mscratch;
uint32_t mepc;
uint32_t mcause;
uint32_t mtval;
uint32_t mhartid; /* ro */
uint32_t misa;
uint32_t mie;
uint32_t mip;
uint32_t medeleg;
uint32_t mideleg;
uint32_t mcounteren;

uint32_t stvec;
uint32_t sscratch;
uint32_t sepc;
uint32_t scause;
uint32_t stval;
uint32_t satp;
uint32_t scounteren;
uint32_t load_res; /* for atomic LR/SC */

// exception causes
#define CAUSE_MISALIGNED_FETCH    0x0
#define CAUSE_FAULT_FETCH         0x1
#define CAUSE_ILLEGAL_INSTRUCTION 0x2
#define CAUSE_BREAKPOINT          0x3
#define CAUSE_MISALIGNED_LOAD     0x4
#define CAUSE_FAULT_LOAD          0x5
#define CAUSE_MISALIGNED_STORE    0x6
#define CAUSE_FAULT_STORE         0x7
#define CAUSE_USER_ECALL          0x8
#define CAUSE_SUPERVISOR_ECALL    0x9
#define CAUSE_HYPERVISOR_ECALL    0xa
#define CAUSE_MACHINE_ECALL       0xb
#define CAUSE_FETCH_PAGE_FAULT    0xc
#define CAUSE_LOAD_PAGE_FAULT     0xd
#define CAUSE_STORE_PAGE_FAULT    0xf
#define CAUSE_INTERRUPT  ((uint32_t)1 << 31)

/* misa CSR */
#define MCPUID_SUPER   (1 << ('S' - 'A'))
#define MCPUID_USER    (1 << ('U' - 'A'))
#define MCPUID_I       (1 << ('I' - 'A'))
#define MCPUID_M       (1 << ('M' - 'A'))
#define MCPUID_A       (1 << ('A' - 'A'))
#define MCPUID_F       (1 << ('F' - 'A'))
#define MCPUID_D       (1 << ('D' - 'A'))
#define MCPUID_Q       (1 << ('Q' - 'A'))
#define MCPUID_C       (1 << ('C' - 'A'))

#define MIP_USIP (1 << 0)
#define MIP_SSIP (1 << 1)
#define MIP_HSIP (1 << 2)
#define MIP_MSIP (1 << 3)
#define MIP_UTIP (1 << 4)
#define MIP_STIP (1 << 5)
#define MIP_HTIP (1 << 6)
#define MIP_MTIP (1 << 7)
#define MIP_UEIP (1 << 8)
#define MIP_SEIP (1 << 9)
#define MIP_HEIP (1 << 10)
#define MIP_MEIP (1 << 11)

/* mstatus CSR */

#define MSTATUS_SPIE_SHIFT 5
#define MSTATUS_MPIE_SHIFT 7
#define MSTATUS_SPP_SHIFT 8
#define MSTATUS_MPP_SHIFT 11
#define MSTATUS_FS_SHIFT 13
#define MSTATUS_UXL_SHIFT 32
#define MSTATUS_SXL_SHIFT 34

#define MSTATUS_UIE (1 << 0)
#define MSTATUS_SIE (1 << 1)
#define MSTATUS_HIE (1 << 2)
#define MSTATUS_MIE (1 << 3)
#define MSTATUS_UPIE (1 << 4)
#define MSTATUS_SPIE (1 << MSTATUS_SPIE_SHIFT)
#define MSTATUS_HPIE (1 << 6)
#define MSTATUS_MPIE (1 << MSTATUS_MPIE_SHIFT)
#define MSTATUS_SPP (1 << MSTATUS_SPP_SHIFT)
#define MSTATUS_HPP (3 << 9)
#define MSTATUS_MPP (3 << MSTATUS_MPP_SHIFT)
#define MSTATUS_FS (3 << MSTATUS_FS_SHIFT)
#define MSTATUS_XS (3 << 15)
#define MSTATUS_MPRV (1 << 17)
#define MSTATUS_SUM (1 << 18)
#define MSTATUS_MXR (1 << 19)
#define MSTATUS_UXL_MASK ((uint64_t)3 << MSTATUS_UXL_SHIFT)
#define MSTATUS_SXL_MASK ((uint64_t)3 << MSTATUS_SXL_SHIFT)

int ctz32(uint32_t a)
{
    int i;
    if (a == 0)
        return 32;
    for(i = 0; i < 32; i++) {
        if ((a >> i) & 1)
            return i;
    }
    return 32;
}

#define SSTATUS_MASK0 (MSTATUS_UIE | MSTATUS_SIE |       \
                      MSTATUS_UPIE | MSTATUS_SPIE |     \
                      MSTATUS_SPP | \
                      MSTATUS_FS | MSTATUS_XS | \
                      MSTATUS_SUM | MSTATUS_MXR)
#define SSTATUS_MASK SSTATUS_MASK0


#define MSTATUS_MASK (MSTATUS_UIE | MSTATUS_SIE | MSTATUS_MIE |      \
                      MSTATUS_UPIE | MSTATUS_SPIE | MSTATUS_MPIE |    \
                      MSTATUS_SPP | MSTATUS_MPP | \
                      MSTATUS_FS | \
                      MSTATUS_MPRV | MSTATUS_SUM | MSTATUS_MXR)

/* cycle and insn counters */
#define COUNTEREN_MASK ((1 << 0) | (1 << 2))

/* return the complete mstatus with the SD bit */
uint32_t get_mstatus(uint32_t mask)
{
    uint32_t val;
    bool sd;
    val = mstatus | (fs << MSTATUS_FS_SHIFT);
    val &= mask;
    sd = ((val & MSTATUS_FS) == MSTATUS_FS) |
         ((val & MSTATUS_XS) == MSTATUS_XS);
    if (sd)
        val |= (uint32_t)1 << (XLEN - 1);
    return val;
}

void set_mstatus(uint32_t val)
{
    fs = (val >> MSTATUS_FS_SHIFT) & 3;

    uint32_t mask = MSTATUS_MASK & ~MSTATUS_FS;
    mstatus = (mstatus & ~mask) | (val & mask);
}

void invalid_csr(uint32_t *pval, uint32_t csr)
{
    /* the 'time' counter is usually emulated */
    if (csr != 0xc01 && csr != 0xc81) {
#ifdef DEBUG_OUTPUT
        printf("csr_read: invalid CSR=0x%x\n", csr);
#endif
    }
    *pval = 0;
}

/* return -1 if invalid CSR. 0 if OK. 'will_write' indicate that the
   csr will be written after (used for CSR access check) */
int csr_read(uint32_t *pval, uint32_t csr,
             bool will_write)
{
    uint32_t val;

    if (((csr & 0xc00) == 0xc00) && will_write)
        return -1; /* read-only CSR */
    if (priv < ((csr >> 8) & 3))
        return -1; /* not enough priviledge */

    switch(csr) {
    case 0xc00: /* ucycle */
    case 0xc02: /* uinstret */
    {
        uint32_t counteren;
        if (priv < PRV_M) {
            if (priv < PRV_S)
                counteren = scounteren;
            else
                counteren = mcounteren;
            if (((counteren >> (csr & 0x1f)) & 1) == 0) {
                invalid_csr(pval, csr);
                return -1;
            }
        }
    }
    val = (int64_t)insn_counter;
    break;
    case 0xc80: /* mcycleh */
    case 0xc82: /* minstreth */
    {
        uint32_t counteren;
        if (priv < PRV_M) {
            if (priv < PRV_S)
                counteren = scounteren;
            else
                counteren = mcounteren;
            if (((counteren >> (csr & 0x1f)) & 1) == 0) {
                invalid_csr(pval, csr);
                return -1;
            }
        }
    }
    val = insn_counter >> 32;
    break;

    case 0x100:
        val = get_mstatus(SSTATUS_MASK);
        break;
    case 0x104: /* sie */
        val = mie & mideleg;
        break;
    case 0x105:
        val = stvec;
        break;
    case 0x106:
        val = scounteren;
        break;
    case 0x140:
        val = sscratch;
        break;
    case 0x141:
        val = sepc;
        break;
    case 0x142:
        val = scause;
        break;
    case 0x143:
        val = stval;
        break;
    case 0x144: /* sip */
        val = mip & mideleg;
        break;
    case 0x180:
        val = satp;
        break;
    case 0x300:
        val = get_mstatus((uint32_t)-1);
        break;
    case 0x301:
        val = misa;
        val |= (uint32_t)mxl << (XLEN - 2);
        break;
    case 0x302:
        val = medeleg;
        break;
    case 0x303:
        val = mideleg;
        break;
    case 0x304:
        val = mie;
        break;
    case 0x305:
        val = mtvec;
        break;
    case 0x306:
        val = mcounteren;
        break;
    case 0x340:
        val = mscratch;
        break;
    case 0x341:
        val = mepc;
        break;
    case 0x342:
        val = mcause;
        break;
    case 0x343:
        val = mtval;
        break;
    case 0x344:
        val = mip;
        break;
    case 0xb00: /* mcycle */
    case 0xb02: /* minstret */
        val = (int64_t)insn_counter;
        break;
    case 0xb80: /* mcycleh */
    case 0xb82: /* minstreth */
        val = insn_counter >> 32;
        break;
    case 0xf14:
        val = mhartid;
        break;
    default:
        invalid_csr(pval, csr);
        //return -1;
        return 0;
    }
    *pval = val;
    return 0;
}

/* return -1 if invalid CSR, 0 if OK, 1 if the interpreter loop must be
   exited (e.g. XLEN was modified), 2 if TLBs have been flushed. */
int csr_write(uint32_t csr, uint32_t val)
{
    uint32_t mask;

#if defined(DUMP_CSR)
    printf("csr_write: csr=0x%03x val=0x", csr);
    print_uint32_t(val);
    printf("\n");
#endif
    switch(csr) {
    case 0x100: /* sstatus */
        set_mstatus((mstatus & ~SSTATUS_MASK) | (val & SSTATUS_MASK));
        break;
    case 0x104: /* sie */
        mask = mideleg;
        mie = (mie & ~mask) | (val & mask);
        break;
    case 0x105:
        stvec = val & ~3;
        break;
    case 0x106:
        scounteren = val & COUNTEREN_MASK;
        break;
    case 0x140:
        sscratch = val;
        break;
    case 0x141:
        sepc = val & ~1;
        break;
    case 0x142:
        scause = val;
        break;
    case 0x143:
        stval = val;
        break;
    case 0x144: /* sip */
        mask = mideleg;
        mip = (mip & ~mask) | (val & mask);
        break;
    case 0x180:
        /* no ASID implemented */
    {
        int new_mode;
        new_mode = (val >> 31) & 1;
        satp = (val & (((uint32_t)1 << 22) - 1)) |
               (new_mode << 31);
    }
    return 2;

    case 0x300:
        set_mstatus(val);
        break;
    case 0x301: /* misa */
        break;
    case 0x302:
        mask = (1 << (CAUSE_STORE_PAGE_FAULT + 1)) - 1;
        medeleg = (medeleg & ~mask) | (val & mask);
        break;
    case 0x303:
        mask = MIP_SSIP | MIP_STIP | MIP_SEIP;
        mideleg = (mideleg & ~mask) | (val & mask);
        break;
    case 0x304:
        mask = MIP_MSIP | MIP_MTIP | MIP_SSIP | MIP_STIP | MIP_SEIP;
        mie = (mie & ~mask) | (val & mask);
        break;
    case 0x305:
        mtvec = val & ~3;
        break;
    case 0x306:
        mcounteren = val & COUNTEREN_MASK;
        break;
    case 0x340:
        mscratch = val;
        break;
    case 0x341:
        mepc = val & ~1;
        break;
    case 0x342:
        mcause = val;
        break;
    case 0x343:
        mtval = val;
        break;
    case 0x344:
        mask = MIP_SSIP | MIP_STIP;
        mip = (mip & ~mask) | (val & mask);
        break;
    default:
        return 0;
        // return -1;
    }
    return 0;
}

void handle_sret()
{
    int spp, spie;
    spp = (mstatus >> MSTATUS_SPP_SHIFT) & 1;
    /* set the IE state to previous IE state */
    spie = (mstatus >> MSTATUS_SPIE_SHIFT) & 1;
    mstatus = (mstatus & ~(1 << spp)) |
              (spie << spp);
    /* set SPIE to 1 */
    mstatus |= MSTATUS_SPIE;
    /* set SPP to U */
    mstatus &= ~MSTATUS_SPP;
    priv = spp;
    next_pc = sepc;
}

void handle_mret()
{
    int mpp, mpie;
    mpp = (mstatus >> MSTATUS_MPP_SHIFT) & 3;
    /* set the IE state to previous IE state */
    mpie = (mstatus >> MSTATUS_MPIE_SHIFT) & 1;
    mstatus = (mstatus & ~(1 << mpp)) |
              (mpie << mpp);
    /* set MPIE to 1 */
    mstatus |= MSTATUS_MPIE;
    /* set MPP to U */
    mstatus &= ~MSTATUS_MPP;
    priv = mpp;
    next_pc = mepc;
}


void raise_exception(uint32_t cause,
                     uint32_t tval)
{
    bool deleg;

    // exit for Zephyr applications
    if (cause == CAUSE_ILLEGAL_INSTRUCTION) {
        machine_running = false;
        return;
    }

    if (priv <= PRV_S) {
        /* delegate the exception to the supervisor priviledge */
        if (cause & CAUSE_INTERRUPT)
            deleg = (mideleg >> (cause & (XLEN - 1))) & 1;
        else
            deleg = (medeleg >> cause) & 1;
    } else {
        deleg = 0;
    }

    if (deleg) {
        scause = cause;
        sepc = pc;
        stval = tval;
        mstatus = (mstatus & ~MSTATUS_SPIE) |
                  (((mstatus >> priv) & 1) << MSTATUS_SPIE_SHIFT);
        mstatus = (mstatus & ~MSTATUS_SPP) |
                  (priv << MSTATUS_SPP_SHIFT);
        mstatus &= ~MSTATUS_SIE;
        priv = PRV_S;
        next_pc = stvec;
    } else {
        mcause = cause;
        mepc = pc;
        mtval = tval;
        mstatus = (mstatus & ~MSTATUS_MPIE) |
                  (((mstatus >> priv) & 1) << MSTATUS_MPIE_SHIFT);
        mstatus = (mstatus & ~MSTATUS_MPP) |
                  (priv << MSTATUS_MPP_SHIFT);
        mstatus &= ~MSTATUS_MIE;
        priv = PRV_M;
        next_pc = mtvec;
    }
}

uint32_t get_pending_irq_mask()
{
    uint32_t pending_ints, enabled_ints;

    pending_ints = mip & mie;
    if (pending_ints == 0)
        return 0;

    enabled_ints = 0;
    switch(priv) {
    case PRV_M:
        if (mstatus & MSTATUS_MIE)
            enabled_ints = ~mideleg;
        break;
    case PRV_S:
        enabled_ints = ~mideleg;
        if (mstatus & MSTATUS_SIE)
            enabled_ints |= mideleg;
        break;
    default:
    case PRV_U:
        enabled_ints = -1;
        break;
    }
    return pending_ints & enabled_ints;
}

int raise_interrupt()
{
    uint32_t mask;
    int irq_num;

    mask = get_pending_irq_mask();
    if (mask == 0)
        return 0;
    irq_num = ctz32(mask);
    raise_exception(irq_num | CAUSE_INTERRUPT, 0);
    return -1;
}

uint32_t get_insn32(uint32_t pc)
{
    uint32_t ptr = pc - ram_start;
    if (ptr > RAM_SIZE) return 1;
    uint8_t* p = ram + ptr;
    return p[0] | (p[1] << 8) | (p[2] << 16) | (p[3] << 24);
}

int target_read_u8(uint8_t *pval, uint32_t addr)
{
    addr -= ram_start;
    if (addr > RAM_SIZE) {
        *pval = 0;
        printf("illegal read 8, PC: 0x%08x, address: 0x%08x\n", pc, addr + ram_start);
        return 1;
    } else {
        uint8_t* p = ram + addr;
        *pval = p[0];
    }
    return 0;
}

int target_read_u16(uint16_t *pval, uint32_t addr)
{
    if (addr & 1) {
        pending_exception = CAUSE_MISALIGNED_LOAD;
        pending_tval = addr;
        return 1;
    }
    addr -= ram_start;
    if (addr > RAM_SIZE)  {
        *pval = 0;
        printf("illegal read 16, PC: 0x%08x, address: 0x%08x\n", pc, addr + ram_start);
        return 1;
    } else {
        uint8_t* p = ram + addr;
        *pval = p[0] | (p[1] << 8);
    }
    return 0;
}

int target_read_u32(uint32_t *pval, uint32_t addr)
{
    if (addr & 3) {
        pending_exception = CAUSE_MISALIGNED_LOAD;
        pending_tval = addr;
        return 1;
    }
    if (addr == MTIMECMP_ADDR) {
        *pval = (uint32_t) mtimecmp;
    } else if (addr == MTIMECMP_ADDR + 4) {
        *pval = (uint32_t) (mtimecmp >> 32);
    } else if (addr == MTIME_ADDR) {
        *pval = (uint32_t) mtime;
    } else if (addr == MTIME_ADDR + 4) {
        *pval = (uint32_t) (mtime >> 32);
    } else {
        addr -= ram_start;
        if (addr > RAM_SIZE) {
            *pval = 0;
            printf("illegal read 32, PC: 0x%08x, address: 0x%08x\n", pc, addr + ram_start);
            return 1;
        } else {
            uint8_t* p = ram + addr;
            *pval = p[0] | (p[1] << 8) | (p[2] << 16) | (p[3] << 24);
        }
    }
    return 0;
}

int target_write_u8(uint32_t addr, uint8_t val)
{
    if (addr == UART_TX_ADDR) {
        // test for UART output, compatible with QEMU
        printf("%c", val);
    } else {
        addr -= ram_start;
        if (addr > RAM_SIZE - 1) {
            printf("illegal write 8, PC: 0x%08x, address: 0x%08x\n", pc, addr + ram_start);
            return 1;
        } else {
            uint8_t* p = ram + addr;
            p[0] = val & 0xff;
        }
    }
    return 0;
}

int target_write_u16(uint32_t addr, uint16_t val)
{
    if (addr & 1) {
        pending_exception = CAUSE_MISALIGNED_STORE;
        pending_tval = addr;
        return 1;
    }
    addr -= ram_start;
    if (addr > RAM_SIZE - 2) {
        printf("illegal write 16, PC: 0x%08x, address: 0x%08x\n", pc, addr + ram_start);
        return 1;
    } else {
        uint8_t* p = ram + addr;
        p[0] = val & 0xff;
        p[1] = (val >> 8) & 0xff;
    }
    return 0;
}

void write_mtimecmp(uint64_t value)
{
    mtimecmp = value;
    mip &= ~MIP_MTIP;
}

int target_write_u32(uint32_t addr, uint32_t val)
{
    if (addr & 3) {
        pending_exception = CAUSE_MISALIGNED_STORE;
        pending_tval = addr;
        return 1;
    }
    if (addr == MTIMECMP_ADDR) {
        write_mtimecmp((mtimecmp & 0xffffffff00000000ll) | val);
    } else if (addr == MTIMECMP_ADDR + 4) {
        write_mtimecmp((mtimecmp & 0xffffffffll) | (((uint64_t)val) << 32));
    } else {
        addr -= ram_start;
        if (addr > RAM_SIZE - 4)  {
            return 1;
        } else {
            uint8_t* p = ram + addr;
            p[0] = val & 0xff;
            p[1] = (val >> 8) & 0xff;
            p[2] = (val >> 16) & 0xff;
            p[3] = (val >> 24) & 0xff;
        }
    }
    return 0;
}

int32_t div32(int32_t a, int32_t b)
{
    if (b == 0) {
        return -1;
    } else if (a == ((int32_t)1 << (XLEN - 1)) && b == -1) {
        return a;
    } else {
        return a / b;
    }
}

uint32_t divu32(uint32_t a, uint32_t b)
{
    if (b == 0) {
        return -1;
    } else {
        return a / b;
    }
}

int32_t rem32(int32_t a, int32_t b)
{
    if (b == 0) {
        return a;
    } else if (a == ((int32_t)1 << (XLEN - 1)) && b == -1) {
        return 0;
    } else {
        return a % b;
    }
}

uint32_t remu32(uint32_t a, uint32_t b)
{
    if (b == 0) {
        return a;
    } else {
        return a % b;
    }
}

static uint32_t mulh32(int32_t a, int32_t b)
{
    return ((int64_t)a * (int64_t)b) >> 32;
}

static uint32_t mulhsu32(int32_t a, uint32_t b)
{
    return ((int64_t)a * (int64_t)b) >> 32;
}

static uint32_t mulhu32(uint32_t a, uint32_t b)
{
    return ((int64_t)a * (int64_t)b) >> 32;
}

/*
// dumps all registers, useful for in-depth debugging
static void dump_regs()
{
    printf("x0 zero: %08x\n", reg[0]);
    printf("x1 ra:   %08x\n", reg[1]);
    printf("x2 sp:   %08x\n", reg[2]);
    printf("x3 gp:   %08x\n", reg[3]);
    printf("x4 tp:   %08x\n", reg[4]);
    printf("x5 t0:   %08x\n", reg[5]);
    printf("x6 t1:   %08x\n", reg[6]);
    printf("x7 t2:   %08x\n", reg[7]);
    printf("x8 s0:   %08x\n", reg[8]);
    printf("x9 s1:   %08x\n", reg[9]);
    printf("x10 a0:  %08x\n", reg[10]);
    printf("x11 a1:  %08x\n", reg[11]);
    printf("x12 a2:  %08x\n", reg[12]);
    printf("x13 a3:  %08x\n", reg[13]);
    printf("x14 a4:  %08x\n", reg[14]);
    printf("x15 a5:  %08x\n", reg[15]);
    printf("x16 a6:  %08x\n", reg[16]);
    printf("x17 a7:  %08x\n", reg[17]);
    printf("x18 s2:  %08x\n", reg[18]);
    printf("x19 s3:  %08x\n", reg[19]);
    printf("x20 s4:  %08x\n", reg[20]);
    printf("x21 s5:  %08x\n", reg[21]);
    printf("x22 s6:  %08x\n", reg[22]);
    printf("x23 s7:  %08x\n", reg[23]);
    printf("x24 s8:  %08x\n", reg[24]);
    printf("x25 s9:  %08x\n", reg[25]);
    printf("x26 s10: %08x\n", reg[26]);
    printf("x27 s11: %08x\n", reg[27]);
    printf("x28 t3:  %08x\n", reg[28]);
    printf("x29 t4:  %08x\n", reg[29]);
    printf("x30 t5:  %08x\n", reg[30]);
    printf("x31 t6:  %08x\n", reg[31]);
}
*/

void execute_instruction()
{
    uint32_t opcode, rd, rs1, rs2, funct3;
    int32_t imm, cond, err;
    uint32_t addr, val, val2;

    opcode = insn & 0x7f;
    rd = (insn >> 7) & 0x1f;
    rs1 = (insn >> 15) & 0x1f;
    rs2 = (insn >> 20) & 0x1f;

    switch(opcode) {
    case 0x37: /* lui */
        if (rd != 0)
            reg[rd] = (int32_t)(insn & 0xfffff000);
        break;
    case 0x17: /* auipc */
        if (rd != 0)
            reg[rd] = (int32_t)(pc + (int32_t)(insn & 0xfffff000));
        break;
    case 0x6f: /* jal */
        imm = ((insn >> (31 - 20)) & (1 << 20)) |
              ((insn >> (21 - 1)) & 0x7fe) |
              ((insn >> (20 - 11)) & (1 << 11)) |
              (insn & 0xff000);
        imm = (imm << 11) >> 11;
        if (rd != 0)
            reg[rd] = pc + 4;
        next_pc = (int32_t)(pc + imm);
        break;
    case 0x67: /* jalr */
        imm = (int32_t)insn >> 20;
        val = pc + 4;
        next_pc = (int32_t)(reg[rs1] + imm) & ~1;
        if (rd != 0)
            reg[rd] = val;
        break;
    case 0x63:
        funct3 = (insn >> 12) & 7;
        switch(funct3 >> 1) {
        case 0: /* beq/bne */
            cond = (reg[rs1] == reg[rs2]);
            break;
        case 2: /* blt/bge */
            cond = ((int32_t)reg[rs1] < (int32_t)reg[rs2]);
            break;
        case 3: /* bltu/bgeu */
            cond = (reg[rs1] < reg[rs2]);
            break;
        default:
            raise_exception(CAUSE_ILLEGAL_INSTRUCTION, insn);
            return;
        }
        cond ^= (funct3 & 1);
        if (cond) {
            imm = ((insn >> (31 - 12)) & (1 << 12)) |
                  ((insn >> (25 - 5)) & 0x7e0) |
                  ((insn >> (8 - 1)) & 0x1e) |
                  ((insn << (11 - 7)) & (1 << 11));
            imm = (imm << 19) >> 19;
            next_pc = (int32_t)(pc + imm);
            break;
        }
        break;
    case 0x03: /* load */
        funct3 = (insn >> 12) & 7;
        imm = (int32_t)insn >> 20;
        addr = reg[rs1] + imm;
        switch(funct3) {
        case 0: /* lb */
        {
            uint8_t rval;
            if (target_read_u8(&rval, addr)) {
                raise_exception(pending_exception, pending_tval);
                return;
            }
            val = (int8_t)rval;
        }
        break;
        case 1: /* lh */
        {
            uint16_t rval;
            if (target_read_u16(&rval, addr)) {
                raise_exception(pending_exception, pending_tval);
                return;
            }
            val = (int16_t)rval;
        }
        break;
        case 2: /* lw */
        {
            uint32_t rval;
            if (target_read_u32(&rval, addr)) {
                raise_exception(pending_exception, pending_tval);
                return;
            }
            val = (int32_t)rval;
        }
        break;
        case 4: /* lbu */
        {
            uint8_t rval;
            if (target_read_u8(&rval, addr)) {
                raise_exception(pending_exception, pending_tval);
                return;
            }
            val = rval;
        }
        break;
        case 5: /* lhu */
        {
            uint16_t rval;
            if (target_read_u16(&rval, addr)) {
                raise_exception(pending_exception, pending_tval);
                return;
            }
            val = rval;
        }
        break;
        default:
            raise_exception(CAUSE_ILLEGAL_INSTRUCTION, insn);
            return;
        }
        if (rd != 0)
            reg[rd] = val;
        break;
    case 0x23: /* store */
        funct3 = (insn >> 12) & 7;
        imm = rd | ((insn >> (25 - 5)) & 0xfe0);
        imm = (imm << 20) >> 20;
        addr = reg[rs1] + imm;
        val = reg[rs2];
        switch(funct3) {
        case 0: /* sb */
            if (target_write_u8(addr, val)) {
                raise_exception(pending_exception, pending_tval);
                return;
            }
            break;
        case 1: /* sh */
            if (target_write_u16(addr, val)) {
                raise_exception(pending_exception, pending_tval);
                return;
            }
            break;
        case 2: /* sw */
            if (target_write_u32(addr, val)) {
                raise_exception(pending_exception, pending_tval);
                return;
            }
            break;
        default:
            raise_exception(CAUSE_ILLEGAL_INSTRUCTION, insn);
            return;
        }
        break;
    case 0x13:
        funct3 = (insn >> 12) & 7;
        imm = (int32_t)insn >> 20;
        switch(funct3) {
        case 0: /* addi */
            val = (int32_t)(reg[rs1] + imm);
            break;
        case 1: /* slli */
            if ((imm & ~(XLEN - 1)) != 0) {
                raise_exception(CAUSE_ILLEGAL_INSTRUCTION, insn);
                return;
            }
            val = (int32_t)(reg[rs1] << (imm & (XLEN - 1)));
            break;
        case 2: /* slti */
            val = (int32_t)reg[rs1] < (int32_t)imm;
            break;
        case 3: /* sltiu */
            val = reg[rs1] < (uint32_t)imm;
            break;
        case 4: /* xori */
            val = reg[rs1] ^ imm;
            break;
        case 5: /* srli/srai */
            if ((imm & ~((XLEN - 1) | 0x400)) != 0) {
                raise_exception(CAUSE_ILLEGAL_INSTRUCTION, insn);
                return;
            }
            if (imm & 0x400)
                val = (int32_t)reg[rs1] >> (imm & (XLEN - 1));
            else
                val = (int32_t)((uint32_t)reg[rs1] >> (imm & (XLEN - 1)));
            break;
        case 6: /* ori */
            val = reg[rs1] | imm;
            break;
        default:
        case 7: /* andi */
            val = reg[rs1] & imm;
            break;
        }
        if (rd != 0)
            reg[rd] = val;
        break;
    case 0x33:
        imm = insn >> 25;
        val = reg[rs1];
        val2 = reg[rs2];
        if (imm == 1) {
            funct3 = (insn >> 12) & 7;
            switch(funct3) {
            case 0: /* mul */
                val = (int32_t)((int32_t)val * (int32_t)val2);
                break;
            case 1: /* mulh */
                val = (int32_t)mulh32(val, val2);
                break;
            case 2:/* mulhsu */
                val = (int32_t)mulhsu32(val, val2);
                break;
            case 3:/* mulhu */
                val = (int32_t)mulhu32(val, val2);
                break;
            case 4:/* div */
                val = div32(val, val2);
                break;
            case 5:/* divu */
                val = (int32_t)divu32(val, val2);
                break;
            case 6:/* rem */
                val = rem32(val, val2);
                break;
            case 7:/* remu */
                val = (int32_t)remu32(val, val2);
                break;
            default:
                raise_exception(CAUSE_ILLEGAL_INSTRUCTION, insn);
                return;
            }
        } else {
            if (imm & ~0x20) {
                raise_exception(CAUSE_ILLEGAL_INSTRUCTION, insn);
                return;
            }
            funct3 = ((insn >> 12) & 7) | ((insn >> (30 - 3)) & (1 << 3));
            switch(funct3) {
            case 0: /* add */
                val = (int32_t)(val + val2);
                break;
            case 0 | 8: /* sub */
                val = (int32_t)(val - val2);
                break;
            case 1: /* sll */
                val = (int32_t)(val << (val2 & (XLEN - 1)));
                break;
            case 2: /* slt */
                val = (int32_t)val < (int32_t)val2;
                break;
            case 3: /* sltu */
                val = val < val2;
                break;
            case 4: /* xor */
                val = val ^ val2;
                break;
            case 5: /* srl */
                val = (int32_t)((uint32_t)val >> (val2 & (XLEN - 1)));
                break;
            case 5 | 8: /* sra */
                val = (int32_t)val >> (val2 & (XLEN - 1));
                break;
            case 6: /* or */
                val = val | val2;
                break;
            case 7: /* and */
                val = val & val2;
                break;
            default:
                raise_exception(CAUSE_ILLEGAL_INSTRUCTION, insn);
                return;
            }
        }
        if (rd != 0)
            reg[rd] = val;
        break;
    case 0x73:
        funct3 = (insn >> 12) & 7;
        imm = insn >> 20;
        if (funct3 & 4)
            val = rs1;
        else
            val = reg[rs1];
        funct3 &= 3;
        switch(funct3) {
        case 1: /* csrrw */
            if (csr_read(&val2, imm, true)) {
                raise_exception(CAUSE_ILLEGAL_INSTRUCTION, insn);
                return;
            }
            val2 = (int32_t)val2;
            err = csr_write(imm, val);
            if (err < 0) {
                raise_exception(CAUSE_ILLEGAL_INSTRUCTION, insn);
                return;
            }
            if (rd != 0)
                reg[rd] = val2;
            if (err > 0) {
                //pc = pc + 4;
            }
            break;
        case 2: /* csrrs */
        case 3: /* csrrc */
            if (csr_read(&val2, imm, (rs1 != 0))) {
                raise_exception(CAUSE_ILLEGAL_INSTRUCTION, insn);
                return;
            }
            val2 = (int32_t)val2;
            if (rs1 != 0) {
                if (funct3 == 2)
                    val = val2 | val;
                else
                    val = val2 & ~val;
                err = csr_write(imm, val);
                if (err < 0) {
                    raise_exception(CAUSE_ILLEGAL_INSTRUCTION, insn);
                    return;
                }
            } else {
                err = 0;
            }
            if (rd != 0)
                reg[rd] = val2;
            break;
        case 0:
            switch(imm) {
            case 0x000: /* ecall */
                if (insn & 0x000fff80) {
                    raise_exception(CAUSE_ILLEGAL_INSTRUCTION, insn);
                    return;
                }
                // compliance test specific: if bit 0 of gp (x3) is 0, it is a syscall,
                // otherwise it is the program end, with the exit code in the bits 31:1
                if (begin_signature) {
                    if (reg[3] & 1) {
#ifdef DEBUG_OUTPUT
                        printf("program end, result: %04x\n", reg[3] >> 1);
#endif
                        machine_running = false;
                        return;
                    } else {
#ifdef DEBUG_OUTPUT
                        printf("syscall: %04x\n", reg[3]);
#endif
                        raise_exception(CAUSE_USER_ECALL + priv, 0);
                    }
                } else {
                    // on real hardware, an exception is raised, the I-ECALL-01 compliance test tests this as well
                    raise_exception(CAUSE_USER_ECALL + priv, 0);
                    return;
                }
                break;

            case 0x001: /* ebreak */
                if (insn & 0x000fff80) {
                    raise_exception(CAUSE_ILLEGAL_INSTRUCTION, insn);
                    return;
                }
                raise_exception(CAUSE_BREAKPOINT, 0);
                return;
            case 0x102: /* sret */
            {
                if ((insn & 0x000fff80) || (priv < PRV_S)) {
                    raise_exception(CAUSE_ILLEGAL_INSTRUCTION, insn);
                    return;
                }
                handle_sret();
                return;
            }
            break;
            case 0x105: /* wfi */
                // wait for interrupt: it is allowed to execute it as nop
                break;
            case 0x302: /* mret */
            {
                if ((insn & 0x000fff80) || (priv < PRV_M)) {
                    raise_exception(CAUSE_ILLEGAL_INSTRUCTION, insn);
                    return;
                }
                handle_mret();
                return;
            }
            break;
            default:
                if ((imm >> 5) == 0x09) {
                    /* sfence.vma */
                    if ((insn & 0x00007f80) || (priv == PRV_U)) {
                        raise_exception(CAUSE_ILLEGAL_INSTRUCTION, insn);
                        return;
                    }
                } else {
                    raise_exception(CAUSE_ILLEGAL_INSTRUCTION, insn);
                    return;
                }
                break;
            }
            break;
        default:
            raise_exception(CAUSE_ILLEGAL_INSTRUCTION, insn);
            return;
        }
        break;
    case 0x0f: /* misc-mem */
        funct3 = (insn >> 12) & 7;
        switch(funct3) {
        case 0: /* fence */
            if (insn & 0xf00fff80) {
                raise_exception(CAUSE_ILLEGAL_INSTRUCTION, insn);
                return;
            }
            break;
        case 1: /* fence.i */
            if (insn != 0x0000100f) {
                raise_exception(CAUSE_ILLEGAL_INSTRUCTION, insn);
                return;
            }
            break;
        default:
            raise_exception(CAUSE_ILLEGAL_INSTRUCTION, insn);
            return;
        }
        break;
    case 0x2f:
        funct3 = (insn >> 12) & 7;
        switch(funct3) {
        case 2:
        {
            uint32_t rval;

            addr = reg[rs1];
            funct3 = insn >> 27;
            switch(funct3) {
            case 2: /* lr.w */
                if (rs2 != 0) {
                    raise_exception(CAUSE_ILLEGAL_INSTRUCTION, insn);
                    return;
                }
                if (target_read_u32(&rval, addr)) {
                    raise_exception(pending_exception, pending_tval);
                    return;
                }
                val = (int32_t)rval;
                load_res = addr;
                break;
            case 3: /* sc.w */
                if (load_res == addr) {
                    if (target_write_u32(addr, reg[rs2])) {
                        raise_exception(pending_exception, pending_tval);
                        return;
                    }
                    val = 0;
                } else {
                    val = 1;
                }
                break;
            case 1: /* amiswap.w */
            case 0: /* amoadd.w */
            case 4: /* amoxor.w */
            case 0xc: /* amoand.w */
            case 0x8: /* amoor.w */
            case 0x10: /* amomin.w */
            case 0x14: /* amomax.w */
            case 0x18: /* amominu.w */
            case 0x1c: /* amomaxu.w */
                if (target_read_u32(&rval, addr)) {
                    raise_exception(pending_exception, pending_tval);
                    return;
                }
                val = (int32_t)rval;
                val2 = reg[rs2];
                switch(funct3) {
                case 1: /* amiswap.w */
                    break;
                case 0: /* amoadd.w */
                    val2 = (int32_t)(val + val2);
                    break;
                case 4: /* amoxor.w */
                    val2 = (int32_t)(val ^ val2);
                    break;
                case 0xc: /* amoand.w */
                    val2 = (int32_t)(val & val2);
                    break;
                case 0x8: /* amoor.w */
                    val2 = (int32_t)(val | val2);
                    break;
                case 0x10: /* amomin.w */
                    if ((int32_t)val < (int32_t)val2)
                        val2 = (int32_t)val;
                    break;
                case 0x14: /* amomax.w */
                    if ((int32_t)val > (int32_t)val2)
                        val2 = (int32_t)val;
                    break;
                case 0x18: /* amominu.w */
                    if ((uint32_t)val < (uint32_t)val2)
                        val2 = (int32_t)val;
                    break;
                case 0x1c: /* amomaxu.w */
                    if ((uint32_t)val > (uint32_t)val2)
                        val2 = (int32_t)val;
                    break;
                default:
                    raise_exception(CAUSE_ILLEGAL_INSTRUCTION, insn);
                    return;
                }
                if (target_write_u32(addr, val2)) {
                    raise_exception(pending_exception, pending_tval);
                    return;
                }
                break;
            default:
                raise_exception(CAUSE_ILLEGAL_INSTRUCTION, insn);
                return;
            }
        }
        break;
        default:
            raise_exception(CAUSE_ILLEGAL_INSTRUCTION, insn);
            return;
        }
        if (rd != 0)
            reg[rd] = val;
        break;
    default:
        raise_exception(CAUSE_ILLEGAL_INSTRUCTION, insn);
        return;
    }
}

// returns realtime in nanoseconds
int64_t get_clock()
{
    struct timespec ts;
    clock_gettime(CLOCK_MONOTONIC, &ts);
    return ts.tv_sec * 1000000000LL + ts.tv_nsec;
}


void riscv_cpu_interp_x32()
{
    /* we use a single execution loop to keep a simple control flow
       for emscripten */
    while (machine_running) {
        // update timer, assuming 10 MHz clock (100 ns period) for the mtime counter
        mtime = get_clock() / 100ll;

        // for reproducible debug runs, you can use a fixed fixed increment per instruction
        //mtime += 10;

        // default value for next PC is next instruction, can be changed by branches or exceptions
        next_pc = pc + 4;

        // test for timer interrupt
        if (mtimecmp <= mtime) {
            mip |= MIP_MTIP;
        }
        if ((mip & mie) != 0 && (mstatus & MSTATUS_MIE)) {
            raise_interrupt();
        } else {
            // normal instruction execution
            insn = get_insn32(pc);
            insn_counter++;

#ifdef DEBUG_OUTPUT
            printf("%08x, mtime: %08x, mtimecmp: %08x\n", pc, mtime, mtimecmp);
#endif
            execute_instruction();
        }

        // test for misaligned fetches
        if (next_pc & 3) {
            raise_exception(CAUSE_MISALIGNED_FETCH, next_pc);
        }

        // update current PC
        pc = next_pc;
    }

#ifdef DEBUG_OUTPUT
    printf("done interp %lx int=%x mstatus=%lx prv=%d\n",
           (uint64_t)insn_counter, mip & mie, (uint64_t)mstatus,
           priv);
#endif
}

int main(int argc, char** argv)
{
    // automatic STDOUT flushing, no fflush needed
    setvbuf(stdout, NULL, _IONBF, 0);

    // parse command line
    const char* elf_file = NULL;
    const char* signature_file = NULL;
    for (int i = 1; i < argc; i++) {
        char* arg = argv[i];
        if (arg == strstr(arg, "+signature=")) {
            signature_file = arg + 11;
        } else if (arg[0] != '-') {
            elf_file = arg;
        }
    }
    if (elf_file == NULL) {
        printf("missing ELF file\n");
        return 1;
    }

    // open ELF file
    elf_version(EV_CURRENT);
    int fd = open(elf_file, O_RDONLY);
    if (fd == -1) {
        printf("can't open file %s\n", elf_file);
        return 1;
    }
    Elf *elf = elf_begin(fd, ELF_C_READ, NULL);

    // scan for symbol table
    Elf_Scn *scn = NULL;
    GElf_Shdr shdr;
    while ((scn = elf_nextscn(elf, scn)) != NULL) {
        gelf_getshdr(scn, &shdr);
        if (shdr.sh_type == SHT_SYMTAB) {
            Elf_Data *data = elf_getdata(scn, NULL);
            int count = shdr.sh_size / shdr.sh_entsize;
            for (int i = 0; i < count; i++) {
                GElf_Sym sym;
                gelf_getsym(data, i, &sym);
                char* name = elf_strptr(elf, shdr.sh_link, sym.st_name);
                if (strcmp(name, "begin_signature") == 0) {
                    begin_signature = sym.st_value;
                }
                if (strcmp(name, "end_signature") == 0) {
                    end_signature = sym.st_value;
                }

                // for compliance test
                if (strcmp(name, "_start") == 0) {
                    ram_start = sym.st_value;
                }

                // for zephyr
                if (strcmp(name, "__reset") == 0) {
                    ram_start = sym.st_value;
                }
                if (strcmp(name, "__irq_wrapper") == 0) {
                    mtvec = sym.st_value;
                }
            }
        }
    }
#ifdef DEBUG_OUTPUT
    printf("begin_signature: 0x%08x\n", begin_signature);
    printf("end_signature: 0x%08x\n", end_signature);
    printf("start: 0x%08x\n", ram_start);
#endif

    // scan for program
    while ((scn = elf_nextscn(elf, scn)) != NULL) {
        gelf_getshdr(scn, &shdr);
        if (shdr.sh_type == SHT_PROGBITS) {
            Elf_Data *data = elf_getdata(scn, NULL);
            if (shdr.sh_addr >= ram_start) {
                for (size_t i = 0; i < shdr.sh_size; i++) {
                    ram[shdr.sh_addr + i - ram_start] = ((uint8_t *)data->d_buf)[i];
                }
            } else {
#ifdef DEBUG_OUTPUT
                printf("ignoring section at address 0x%08x\n", (uint32_t) shdr.sh_addr);
#endif
            }
        }
    }

    // close ELF file
    elf_end(elf);
    close(fd);

    // run program in emulator
    pc = ram_start;
    riscv_cpu_interp_x32();

    // write signature
    if (signature_file) {
        FILE* sf = fopen(signature_file, "w");
        int size = end_signature - begin_signature;
        for (int i = 0; i < size / 16; i++) {
            for (int j = 0; j < 16; j++) {
                fprintf(sf, "%02x", ram[begin_signature + 15 - j - ram_start]);
            }
            begin_signature += 16;
            fprintf(sf, "\n");
        }
        fclose(sf);
    }

    return 0;
}
```