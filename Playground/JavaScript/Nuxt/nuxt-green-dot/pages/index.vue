<template>
  <div class="body">
    <div class="gd-ct" refs="gd_ct" @mousedown="onTouchStart">
      <ul class="gd-handle" refs="gd_handle" style="--btncount:8;--rotate:0deg;transform:rotate(0deg);">
        <li style="--i:0;"><a href="#">버튼1</a></li>
        <li style="--i:1;"><a href="#">버튼2</a></li>
        <li style="--i:2;"><a href="#">버튼3</a></li>
        <li style="--i:3;"><a href="#">버튼4</a></li>
        <li style="--i:4;"><a href="#">버튼5</a></li>
        <li style="--i:5;"><a href="#">버튼6</a></li>
        <li style="--i:6;"><a href="#">버튼7</a></li>
        <li style="--i:7;"><a href="#">버튼8</a></li>
      </ul>
    </div>
  </div>
</template>

<script>
export default {
  name: 'IndexPage',
  data() {
    return {
      gdCt: null,
      gdHandle: null,
      startDegree: 0,
      startPosRadian: 0,
      centerPos: null,
      startPos: null,
      isTouching: false,

    }
  },
  mounted() {
    window.addEventListener("mousemove", this.onTouchMove);
    window.addEventListener("touchmove", this.onTouchMove);
    window.addEventListener("mouseup", this.onTouchEnd);
    window.addEventListener("touchend", this.onTouchEnd);
  },
  methods: {
    onTouchStart(e) {
      console.log(e);
      this.gdCt = document.querySelector('.gd-ct');
      this.gdHandle = this.gdCt.querySelector(".gd-handle")
      const centerRect = this.gdCt.getBoundingClientRect();
      const startPos = {
        left: e.touches ? e.touches?.[0].clientX : e.clientX,
        top: e.touches ? e.touches[0].clientY : e.clientY,
      };

      this.centerPos = {
        left: centerRect.left + centerRect.width / 2,
        top: centerRect.top + centerRect.height / 2,
      };

      this.startDegree = this.gdHandle.style.getPropertyValue('--rotate');
      this.startPosRadian = Math.atan2(
        this.centerPos.top - startPos.top,
        this.centerPos.left - startPos.left,
      );
      this.isTouching = true;
    },
    onTouchMove(e) {
      console.log(e);

      if (!this.isTouching) return;

      const x = this.centerPos.left - (e.touches ? e.touches[0].clientX : e.clientX);
      const y = this.centerPos.top - (e.touches ? e.touches[0].clientY : e.clientY);
      const radian = Math.atan2(y, x);
      const radianDiff = radian - this.startPosRadian;
      const degreeDiff = radianDiff * 180 / Math.PI;
      const finalDegree = parseInt(this.startDegree, 10) + degreeDiff;

      this.gdHandle.style.setProperty('transform', `rotate(${finalDegree}deg)`);
      this.gdHandle.style.setProperty('--rotate', `${finalDegree}deg`);
    },
    onTouchEnd(e) {
      console.log(e);
      if (!this.isTouching) return;
      this.isTouching = false;
    }
  }
}
</script>

<style scoped>
.body {
  background-color: #ddd;
}

.gd-ct {
  position: absolute;
  left: 50%;
  top: 50%;
  transform: translate(-50%, -50%);
  background-color: #fff;
  width: 320px;
  height: 320px;
  overflow: hidden;
  border-radius: 50%;
  user-select: none;
}

.gd-ct ul {
  position: absolute;
  list-style: none;
  padding: 0;
  margin: 0;
  left: 0;
  top: 0;
  right: 0;
  bottom: 0;
}

.gd-ct li {
  position: absolute;
  left: calc(50%);
  top: 60px;
  transform: translate(-50%, -50%) rotate(calc(360deg / var(--btncount) * var(--i)));
  transform-origin: 30px 130px;
}

.gd-ct li a {
  display: flex;
  width: 60px;
  height: 60px;
  background-color: #ddd;
  border-radius: 50%;
  justify-content: center;
  align-items: center;
  text-decoration: none;
  color: #333;
  user-select: none;
  transform: rotate(calc(-1 * 360deg / var(--btncount) * var(--i) - var(--rotate)));
}

.gd-ct li:nth-child(even) a {
  background-color: #999;
}

</style>
