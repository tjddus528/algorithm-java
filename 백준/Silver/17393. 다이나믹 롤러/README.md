# [Silver III] 다이나믹 롤러 - 17393 

[문제 링크](https://www.acmicpc.net/problem/17393) 

### 성능 요약

메모리: 210760 KB, 시간: 1100 ms

### 분류

이분 탐색

### 제출 일자

2025년 6월 18일 23:51:42

### 문제 설명

<p>페인팅 전문 회사 부키치 암즈는 거대한 페인팅용 롤러 "다이나믹 롤러"를 출시하였다. 이 신제품은 평범한 페인팅 롤러와 마찬가지로 굴려서 칠할 수 있지만, 손잡이를 세로로 휘둘러 잉크를 한번에 흩뿌릴 수도 있도록 설계되었다. 이러한 새로운 사용방법은 거대한 몸집과 맞물려 매우 역동적으로 보였기 때문에, 이 롤러의 이름은 다이나믹 롤러가 되었다. 평소 페인팅에 관심이 많던 멩미는 다이나믹 롤러의 매력에 흠뻑 빠져, 단숨에 다이나믹 롤러를 구매했다. 지금 당장 롤러를 시험해 보고 싶었던 멩미는 통로 일부분을 칠해보기로 했다.</p>

<p>통로는 1 × <em>N</em> 길이의 일자 형태를 가지고 있고, 통로의 바닥은 1 × 1 타일로 가득 차있다. 각 타일은 잉크지수 <em>A<sub>i</sub></em> 와 점도지수 <em>B<sub>i </sub></em>를 가지고 있다. 타일이 제각각 다른 특성을 가지고 있기 때문에, 멩미는 세심하게 롤러를 휘둘러야만 한다. 멩미가 <em>i</em> 번째 타일 위에 서 있을 때, 멩미는 다이나믹 롤러로 현재 위치보다 오른쪽에 있으면서 점도지수가 서 있는 칸의 잉크지수 <em>A<sub>i</sub></em> 이하인 칸을 칠할 수 있다.</p>

<p>통로는 기본적인 관리가 되고 있기 때문에, 각 칸의 잉크지수 <em>A<sub>i</sub></em> 는 점도지수 <em>B<sub>i</sub></em> 이상이다. 그러나 깊숙한 통로는 관리에 어려움이 있기 때문에, 점도지수 <em>B<sub>i </sub></em>는 항상 오름차순이다. 이런 상황 속에서 멩미가 통로의 각 타일에서 서 있을 때 다이나믹 롤러로 칠할 수 있는 최대의 칸 수를 구해보자.</p>

### 입력 

 <p>첫 번째 줄에 통로의 길이인 자연수 <em>N</em>이 입력으로 주어진다. (1 ≤ <em>N</em> ≤ 5 × 10<sup>5</sup>)</p>

<p>두 번째 줄에 <em>N</em>개의 정수 <em>A</em><sub>1</sub>, <em>A</em><sub>2</sub>, ..., <em>A<sub>N</sub></em>이 공백으로 구분되어 주어진다. <em>A<sub>i</sub></em> 는 <em>i</em> 번째 칸의 잉크지수를 의미한다. (1 ≤ <em>Aᵢ</em> ≤ 10<sup>18</sup>)</p>

<p>세 번째 줄에 <em>N</em>개의 정수 <em>B</em><sub>1</sub>, <em>B</em><sub>2</sub>, ..., <em>B<sub>N</sub></em>이 공백으로 구분되어 주어진다. <em>B<sub>i</sub></em> 는 <em>i</em> 번째 칸의 점도지수를 의미한다. (1 ≤ <em>Bᵢ</em> ≤ 10<sup>18</sup>, <em>A<sub>i</sub></em> ≥ <em>B<sub>i</sub></em>)</p>

<p><em>B</em><sub>1</sub>, <em>B</em><sub>2</sub>, ..., <em>B<sub>N</sub></em>은 오름차순이다. 즉, 1 ≤ <em>i</em> ≤ <em>j</em> ≤ <em>N</em>을 만족하는 모든 정수 순서쌍 (<em>i</em>, <em>j</em>)에 대해 <em>B<sub>i</sub></em> ≤ <em>B<sub>j</sub></em> 가 성립한다.</p>

### 출력 

 <p>첫 번째 줄에 <em>N</em>개의 정수 <em>x</em><sub>1</sub>, <em>x</em><sub>2</sub>, ..., <em>x<sub>N</sub></em>을 공백으로 구분하여 출력한다. <em>x<sub>i</sub></em>는 <em>i</em> 번째 칸에 서서 다이나믹 롤러를 사용할 때 최대로 칠할 수 있는 칸의 개수이다.</p>

