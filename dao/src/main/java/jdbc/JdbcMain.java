package jdbc;




import jdbc.dao.*;
import jdbc.vo.LoLChamVO;
import jdbc.vo.LoLPbVO;
import jdbc.vo.LoLSkinVO;

import java.util.List;
import java.util.Scanner;

public class JdbcMain {
    private static String loginSuccess = "";

    public static String getLoginSuccess() {
        return loginSuccess;
    }
    private static String selCham = "";

    public static String getSelCham() {
        return selCham;
    }
    private static String CHPname = "";

    public static String getCHPname() {
        return CHPname;
    }
    private static String selSkin = "";

    public static void main(String[] args) {
//        LoLChamDAO loLChamDAO = new LoLChamDAO();
//        List<LoLChamVO> list7 = loLChamDAO.UserLoLChamDAO("sks", id);
//        loLChamDAO.UserLoLSelectPrint(list7);
        Scanner sc = new Scanner(System.in);
        boolean Success = false;
        while (true) {
            System.out.println("====== [EMP Table Command] =======");
            System.out.println("메뉴를 선택하세요 : ");
            System.out.print("[1]로그인, [2]회원가입 [3]종료 ");
            int sel = sc.nextInt();
            sc.nextLine();
            MemberDAO memberdao = new MemberDAO();
            switch (sel) {
                case 1:
                    while (true) {
                        JdbcMain main = new JdbcMain();
                        if (!loginSuccess.equals("실패")) {
                            Success = true;
                            System.out.println(loginSuccess + "님 환영합니다.");
                            break;
                        } else {
                            System.out.println("잘못된 ID입니다.");
                        }
                    }
                    break;
                case 2:
                    System.out.println(memberdao.getCurrentID() + "님 환영합니다.");
                    Success = true;
                    break;
                case 3:
                    System.out.println("종료");
                    Success = false;
                    break;
            }
            if (Success) {
                while (true) {

                    System.out.println("=============");
                    System.out.println("메뉴를 선택하세요 : ");
                    System.out.print("[1]게임시작, [2]상점, [3]로그아웃 ");
                    int sel2 = sc.nextInt();
                    sc.nextLine();
                    LoLSkinDAO lolskindao = new LoLSkinDAO();
                    switch (sel2) {
                        case 1:
//                            System.out.println("게임을 시작합니다.");
//                            LoLChamDAO lolchamdao = new LoLChamDAO();
//                            List<LoLChamVO> list3 = lolchamdao.UserLoLChamDAO("sks");
//                            lolchamdao.UserLoLSelectPrint(list3);
//                            System.out.println("챔피언을 선택해주세요");
//                            selCham = sc.next();
//                            sc.nextLine();
//                            List<LoLSkinVO> list4 = lolskindao.UserLoLSkinDAO(selCham);
//                            lolskindao.UserLoLSkinSelectPrint(list4);
//                            selSkin = sc.nextLine();
//                            System.out.println("게임을 시작합니다.");
//                            GameDAO gameDAO = new GameDAO();
//                            gameDAO.WinLose();
                            break;
                        case 2:
                            System.out.println("어떤 상점에 입장하시겠습니까 : ");
                            System.out.print("[1]챔피언 상점, [2]스킨 상점 [3] 구매내역 ");
                            int shopSel = sc.nextInt();
                            sc.nextLine();
                            if (shopSel == 1) {
//                                List<LoLChamVO> list = lolchamdao.LoLChamSelect();
//                                lolchamdao.LoLSelectPrint(list);
                                System.out.println("===============================================");
                                System.out.println(loginSuccess + "님의 현재 보유 골드 : " + memberdao.userGold(loginSuccess));
                                System.out.println("===============================================");
//                                lolchamdao.LoLChamInsert();
                            }
                            if (shopSel == 2) {
                                List<LoLSkinVO> SKlist = lolskindao.LoLSkinSelect();
                                lolskindao.LoLSelectPrint(SKlist);

                                List<LoLSkinVO> list2 = lolskindao.LoLSkinBuySelect();
                                lolskindao.LoLSKinBuySelectPrint(list2);

                                System.out.println("===============================================");
                                System.out.println(loginSuccess + "님의 현재 보유 골드 : " + memberdao.userGold(loginSuccess));
                                System.out.println("===============================================");
//
//                                lolskindao.LoLSkinInsert(SkinBuyName, id);
                            }
                            if(shopSel == 3){
                                LoLPbDAO lolpbdao = new LoLPbDAO();
                                List<LoLPbVO> list = lolpbdao.LoLPbSelect();
                                lolpbdao.LoLPdSelectPrint(list);
                            }
                            break;
                        case 3:
                            System.out.println("로그아웃합니다");
                            break;
                    }
                    if(sel2 == 3) {
                        break;
                    }
                    if(sel2 == 1){
                        System.out.println(selCham);
                        System.out.println(selSkin);
                    }
                }
            }if(sel == 3){
                break;
            }
        }
    }

}