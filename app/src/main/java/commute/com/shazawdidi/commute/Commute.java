package commute.com.shazawdidi.commute;

public class Commute {
    String clientId,FirstPoint,LastPoint,FirstTime,LastTime;

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public void setFirstPoint(String firstPoint) {
        FirstPoint = firstPoint;
    }

    public void setLastPoint(String lastPoint) {
        LastPoint = lastPoint;
    }

    public void setFirstTime(String firstTime) {
        FirstTime = firstTime;
    }

    public void setLastTime(String lastTime) {
        LastTime = lastTime;
    }

    public String getClientId() {
        return clientId;
    }

    public String getFirstPoint() {
        return FirstPoint;
    }

    public String getLastPoint() {
        return LastPoint;
    }

    public String getFirstTime() {
        return FirstTime;
    }

    public String getLastTime() {
        return LastTime;
    }
}
