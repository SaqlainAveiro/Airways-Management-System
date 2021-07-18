package fxmldemo;

/**
 *
 * @author Aristo_PC
 */
public class FlightHistory {
    
    String id,src,dst,date,selected,nos;

    public String getSelected() {
        return selected;
    }

    public void setSelected(String selected) {
        this.selected = selected;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSrc() {
        return src;
    }

    public void setSrc(String src) {
        this.src = src;
    }

    public String getDst() {
        return dst;
    }

    public void setDst(String dst) {
        this.dst = dst;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getNos() {
        return nos;
    }

    public void setNos(String nos) {
        this.nos = nos;
    }

    public FlightHistory(String id, String src, String dst, String date,String selected, String nos) {
        this.id = id;
        this.src = src;
        this.dst = dst;
        this.date = date;
        this.selected = selected;
        this.nos = nos;
    }

}
