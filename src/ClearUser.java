public class ClearUser {
    private int stage; // 최종 스테이지
    private String name; // 닉네임

    public ClearUser(int stage, String name) {
        this.stage = stage;
        this.name = name;
    }

    public int getStage() {
        return stage;
    }

    public void setStage(int stage) {
        this.stage = stage;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
