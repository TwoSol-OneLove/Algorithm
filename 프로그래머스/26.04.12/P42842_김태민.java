/**
 걍 갯수를 합쳐서 직사각형 만들기?
조건
 - 가로 길이>=세로길이
 
설계
 - 일단 노란색 부분을 브라운 색이 감싸야함
 - 일단 노란색의 가로 길이가x이면 브라운색깔 가로 길이 = (x+2)*2;
 - 노란색의 세로 길이가 y이면 브라운 색깔이 세로 길이 = y*2;
 - 노란색 크기(yellow)+ 브라운(brown) = (x+2)*2 + y*2;
 - 세로 길이 = yellow/x--> 나눴을 때 나머지는 0이여야함
 그래서 x값을 키워가면서 조건들에 맞는지 보기 
 

*/

class Solution {
    public int[] solution(int brown, int yellow) {
        for (int h = 1; h <= yellow; h++) {
            // yellow로 h높이의 직사각형을 만들 수 있는지 확인
            if (yellow % h == 0) {
                int w = yellow / h;

                // 문제 조건: 가로 >= 세로
                if (w < h) continue;

                // 전체 카펫 넓이 = brown + yellow
                if ((w + 2) * (h + 2) == brown + yellow) {
                    return new int[]{w + 2, h + 2};
                }
            }
        }
        return new int[0];
    }
}