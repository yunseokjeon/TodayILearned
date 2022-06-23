const gdCt = document.querySelector(".gd-ct");
const gdHandle = gdCt.querySelector(".gd-handle");

let startDegree;
let startRadian;
let centerPos;
let isTouching = false;

const onTouchStart = e => {
  const centerRect = gdCt.getBoundingClientRect();
  const startPos = {
    left: e.touches ? e.touches?.[0].clientX : e.clientX,
    top: e.touches ? e.touches[0].clientY : e.clientY,
  };

  centerPos = {
    left: centerRect.left + centerRect.width / 2,
    top: centerRect.top + centerRect.height / 2,
  };
  startDegree = gdHandle.style.getPropertyValue('--rotate');
  startPosRadian = Math.atan2(
    centerPos.top - startPos.top,
    centerPos.left - startPos.left,
  );

  isTouching = true;
};

const onTouchMove = e => {
  if (!isTouching) return;

  const x = centerPos.left - (e.touches ? e.touches[0].clientX : e.clientX);
  const y = centerPos.top - (e.touches ? e.touches[0].clientY : e.clientY);
  const radian = Math.atan2(y, x);
  const radianDiff = radian - startPosRadian;
  const degreeDiff = radianDiff * 180 / Math.PI;
  const finalDegree = parseInt(startDegree, 10) + degreeDiff;

  gdHandle.style.setProperty('transform', `rotate(${finalDegree}deg)`);
  gdHandle.style.setProperty('--rotate', `${finalDegree}deg`);
};

const onTouchEnd = e => {
  if (!isTouching) return;
  isTouching = false;
};

gdCt.addEventListener("mousedown", onTouchStart);
gdCt.addEventListener("touchstart", onTouchStart);
window.addEventListener("mousemove", onTouchMove);
window.addEventListener("touchmove", onTouchMove);
window.addEventListener("mouseup", onTouchEnd);
window.addEventListener("touchend", onTouchEnd);