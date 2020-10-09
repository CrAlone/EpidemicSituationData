package domian;

import java.util.ArrayList;
import java.util.List;

/**
 * 员工表
 */
public class User {
    /**
     * 员工id
     */
    private int uid;
    /**
     * 上一级id
     */
    private int pid;
    /**
     * 员工名字
     */
    private String name;
    /**
     * 业绩
     */
    private double performance;
    /**
     * 职位id
     */
    private int post=1;
    /**
     * 下一级所有员工
     */
    private List<User> childrenList;
    public User() {
        this.childrenList = new ArrayList<>();
    }
    public User(int uid, int pid, String name, double performance, int post, List<User> childrenList) {
        this.uid = uid;
        this.pid = pid;
        this.name = name;
        this.performance = performance;
        this.post = post;
        this.childrenList = childrenList;
    }
    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPerformance() {
        return performance;
    }

    public void setPerformance(double performance) {
        this.performance = performance;
    }

    public int getPost() {
        return post;
    }

    public void setPost(int post) {
        this.post = post;
//        if (this.childrenList.size() >= 30 && this.performance >= 600000){
//            this.post = 6;
//            return;
//        }else if (this.childrenList.size() >= 25 && this.performance >= 250000){
//            this.post = 5;
//            return;
//        }else if (this.childrenList.size() >= 15 && this.performance >= 90000){
//            this.post = 4;
//            return;
//        }else if (this.childrenList.size() >=10  && this.performance >= 30000){
//            this.post = 3;
//            return;
//        }else if (this.childrenList.size() >=5 && this.performance >=10000) {
//            this.post = 2;
//            return;
//        }else {
//            this.post = 1;
//            return;
//        }

    }

    public List<User> getChildrenList() {
        return childrenList;
    }

    public void setChildrenList(List<User> childrenList) {
        this.childrenList = childrenList;
    }
}
