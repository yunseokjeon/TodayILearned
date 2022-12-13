/*
WebStorm으로 TypeScript debug 모드 사용하기
https://ivvve.github.io/2019/12/17/js/ts/typescript-webstorm-debug/
 */

class TreeNode {
    val: number
    left: TreeNode | null
    right: TreeNode | null

    constructor(val?: number, left?: TreeNode | null, right?: TreeNode | null) {
        this.val = (val === undefined ? 0 : val)
        this.left = (left === undefined ? null : left)
        this.right = (right === undefined ? null : right)
    }
}



