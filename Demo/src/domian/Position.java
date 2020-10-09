package domian;

/**
 * 职位
 */
public class Position {
    public Position() {
    }
    public Position(int id, String name, int pid, double performance, double commission) {
        this.id = id;
        this.name = name;
        this.pid = pid;
        this.performance = performance;
        this.commission = commission;
    }
    /**
     * 职位编号
     * 1是普员工 2店长 3主管 4经理 5总监 6懂事
     */
    private int id;
    /**
     * 职位名称
     */
    private String name;
    /**
     * 上一级
     */
    private int pid;
    /**
     * 业绩
     */
    private double performance;
    /**
     * 提成比列
     */
    private double commission;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    public double getPerformance() {
        return performance;
    }

    public void setPerformance(double performance) {
        this.performance = performance;
    }

    public double getCommission() {
        return commission;
    }

    public void setCommission(double commission) {
        this.commission = commission;
    }
}
