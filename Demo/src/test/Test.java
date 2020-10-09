package test;

import domian.Position;
import domian.User;

import java.util.ArrayList;
import java.util.List;


public class Test {
    public static void main(String[] args) {
        //公司所有员工
        List<User> userList = new ArrayList<>();
        //所有职位
        List<Position> positionList = new ArrayList<>();

        //初始化员工和职位
        {
            List<User> levelOneList = new ArrayList<>();
            List<User> levelOneList2 = new ArrayList<>();
            //创建6个普通员工
            for(int i = 0 ;i<6;i++){
                User user = new User();
                //员工id
                user.setUid(i+1);
                //员工名称
                user.setName("陈"+(i+1));
                //上一级
                user.setPid(13);
                //普通员工业绩
                user.setPerformance(200*i);
                //普通员工职位
                user.setPost(1);
                //普通员工的分销人员
                List<User> childrenList = new ArrayList<>();
                for (int j = 0 ;j <i;j++){
                    User user2 = new User();
                    //员工id
                    user2.setUid(100+j);
                    //员工名称
                    user2.setName("李"+(j+1));
                    //上一级 id
                    user2.setPid(i);
                    //普通员工业绩
                    user2.setPerformance(0);
                    //普通员工职位
                    user2.setPost(1);
                    childrenList.add(user2);
                    userList.add(user2);
                }
                user.setChildrenList(childrenList);
                levelOneList.add(user);
                userList.add(user);
            }
            for(int i = 6 ;i<11;i++){
                User user = new User();
                //员工id
                user.setUid(i+1);
                //员工名称
                user.setName("陈"+(i+1));
                //上一级
                user.setPid(007);
                //普通员工业绩
                user.setPerformance(200);
                //普通员工职位
                user.setPost(1);
                //普通员工的分销人员
                User user2 = new User();
                List<User> childrenList = new ArrayList<>();
                //员工id
                user2.setUid(110+i);
                //员工名称
                user2.setName("李"+(i+6));
                //上一级 id
                user2.setPid(i);
                //普通员工业绩
                user2.setPerformance(0);
                //普通员工职位
                user2.setPost(1);
                childrenList.add(user2);
                user.setChildrenList(childrenList);
                userList.add(user);
                levelOneList2.add(user);
                userList.add(user2);
            }

            //第一个店长
            User user = new User();
            user.setUid(13);
            user.setPid(14);
            user.setName("张1");
            user.setPerformance(10000);
            user.setPost(2);
            user.setChildrenList(levelOneList);
            userList.add(user);

            //第二个店长
            User user2 = new User();
            user2.setUid(007);
            user2.setPid(14);
            user2.setName("张2");
            user2.setPerformance(10000);
            user2.setChildrenList(levelOneList2);
            user2.setPost(2);
            userList.add(user2);

            //创建主管分销的10人
            List<User> levelTwoList = new ArrayList<>();
            for (int i = 0 ;i<10;i++){
                User user3 = new User();
                user3.setUid(20+i);
                user3.setName("王"+(i+1));
                user3.setPost(1);
                user3.setPerformance(0);
                user3.setPid(13);
                userList.add(user3);
                levelTwoList.add(user3);
            }
            levelTwoList.add(user);
            levelTwoList.add(user2);
            //主管
            User user4 = new User();
            user4.setUid(14);
            user4.setPid(15);
            user4.setName("赵1");
            user4.setPerformance(30000);
            user4.setChildrenList(levelTwoList);
            user4.setPost(3);
            userList.add(user4);
            //------------下面层次以此类推

            //普通员工职位
            Position position1 = new Position();
            position1.setId(1);
            position1.setName("普通员工");
            position1.setPid(2);
            position1.setPerformance(0);
            position1.setCommission(0.02);

            //店长职位
            Position position2 = new Position();
            position2.setId(2);
            position2.setName("店长");
            position2.setPid(3);
            position2.setPerformance(10000);
            position2.setCommission(0.05);

            //主管
            Position position3 = new Position();
            position3.setId(3);
            position3.setName("主管");
            position3.setPid(4);
            position3.setPerformance(30000);
            position3.setCommission(0.08);

            positionList.add(position1);
            positionList.add(position2);
            positionList.add(position3);
        }

        //主管提成 id =13
        User manager = new User();
        double commission = 0;
        for (User user1:userList) {
            if (user1.getUid()==14){
                manager = user1;
                for (Position position:positionList){
                    if (user1.getPost() == position.getId()){
                        commission = position.getCommission();
                    }
                }
            }
        }
        double sal = manager.getPerformance() * commission;
        System.out.println(manager.getName()+"月底分红为："+sal);


        //店长提成 id =007/13
        User shopowner1 = new User();
        double commission1 = 0;
        double sal1 = 0;
        for (User user1:userList) {
            if (user1.getUid() == 007) {
                shopowner1 = user1;
                for (Position position : positionList) {
                    if (shopowner1.getPost() == position.getId()) {
                        commission1 = position.getCommission();

                    }
                }
                if (shopowner1.getChildrenList().size() == 5) {
                    sal1 = shopowner1.getPerformance() * commission1;
                } else if (user1.getChildrenList().size() > 5) {
                    int count = user1.getChildrenList().size() - 5;
                    sal1 = shopowner1.getPerformance() * commission1 + (count * 200*0.35);
                }
            }
        }
        System.out.println(shopowner1.getName()+"月底分红为："+sal1);

        //店长提成 id =007/13
        User shopowner2 = new User();
        double commission2 = 0;
        double sal2 = 0;
        for (User user:userList) {
            if (user.getUid()==13){
                shopowner2 = user;
                for (Position position:positionList){
                    if (shopowner2.getPost() == position.getId()){
                        commission2 = position.getCommission();
                    }
                }
                if (shopowner2.getChildrenList().size() ==5){
                    sal2 = shopowner1.getPerformance() * commission2;
                }else if (user.getChildrenList().size() > 5){
                    int count = user.getChildrenList().size() - 5;
                    sal2 = shopowner1.getPerformance() * commission2 + (count * 200*0.35);
                }
            }
        }
        System.out.println(shopowner2.getName()+"月底分红为："+sal2);
    }

}
