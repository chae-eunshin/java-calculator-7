package calculator;

import camp.nextstep.edu.missionutils.Console;
import java.util.Arrays;

public class Application {

  public static void main(String[] args) {

    //구분자를 기억하고 있는 배열

    String[] divisionList = {",", ":"};

    System.out.println("덧셈할 문자열을 입력해 주세요. ");
    String input = Console.readLine();

    int start = 0;
    int result = 0;

    String divisionString = "";
    String realInput = input; //기본적으로는 입력값 자체가 들어감

    for (int i = 0; i < input.length(); i++) { //커스터 마이징이 있는 경우에 한해서만 substring을 해줌
      char c = input.charAt(i);

      if (c == '/') {
        start += 1; //처음에 /를 통해 조건문에 들어오면 1이 될 것이고, 그 다음에 들어올 때는 2
      } else if (c == '\\') { // \가 시작된다는 건 구분자 설정이 끝난다는 의미
        char cNext = input.charAt(i + 1);
        if (cNext == 'n') { //백슬래시 다음에 n이 나온다면 이제 구분자 커스터 마이징은 끝남
          realInput = input.substring(i + 2); //구분자를 커스터 마이징하는 부분을 제외하고 부분 문자열을 할당
          //System.out.println(input.substring(i + 1));
          break;
        }
      } else { //커스터마이징 시작 문구도 끝문구도 아닌경우
        if (start == 2) { //커스터 마이징 문구를 추가하기 시작했다는 뜻
          divisionString += c;
        }
      }

    }
    //입력 받은 구분자 문자열을 빈문자열을 split에 구분자로 넣어 배열로 만듦
    String[] divisionList2 = divisionString.split("");
    //기존에 가지고 있던 구분자의 배열과 입력을 받은 구분자의 배열의 길이를 합쳐서 최종 구분자 배열의 길이를 정해줌
    String[] finalDivision = new String[divisionList.length + divisionList2.length];

    System.arraycopy(divisionList, 0, finalDivision, 0, divisionList.length);
    System.arraycopy(divisionList2, 0, finalDivision, divisionList.length, divisionList2.length);

    String nums = "";
//    System.out.println("중간에 결과 한번 보자" + realInput);
    for (int j = 0; j < realInput.length(); j++) {

      String element = Character.toString(realInput.charAt(j));

      if (j == 0 && element.matches("\\d+")) {
        nums += element;
      }
      boolean isDivision = false;
      if (Arrays.asList(finalDivision).contains(element)) { //구분자가 맞는 경우
        isDivision = true;
        if (nums.matches(("\\d+"))) {
          result = result + Integer.parseInt(nums);
          nums = ""; //숫자 초기화
        }
      } else if (!element.matches("\\d+")) { //구분자도 아닌데 정수도 아닌 경우의 문자
        //System.out.println(element + "에러를 내는거지?");
        throw new IllegalArgumentException();
      }

      if (isDivision) {
        continue;
      }
      if (j != 0 && element.matches("\\d+")) { //숫자인 경우
        nums += element;
      }
      if (j == realInput.length() - 1 && element.matches("\\d+")) {
        result = result + Integer.parseInt(nums);
      }
    }
    System.out.println("결과 : " + result);

  }
}
