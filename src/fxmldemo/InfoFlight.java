package fxmldemo;

/**
 *
 * @author Aristo_PC
 */
public class InfoFlight {
    String id,src,dst,ddt,clstype;Integer fare,rdt;

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

    public String getDdt() {
        return ddt;
    }

    public void setDdt(String ddt) {
        this.ddt = ddt;
    }

    public Integer getRdt() {
        return rdt;
    }

    public void setRdt(Integer rdt) {
        this.rdt = rdt;
    }

    public String getClstype() {
        return clstype;
    }

    public void setClstype(String clstype) {
        this.clstype = clstype;
    }

    public Integer getFare() {
        return fare;
    }

    public void setFare(Integer fare) {
        this.fare = fare;
    }

    public InfoFlight(String id, String src, String dst, String ddt, Integer rdt, String clstype, Integer fare) {
        this.id = id;
        this.src = src;
        this.dst = dst;
        this.ddt = ddt;
        this.rdt = rdt;
        this.clstype = clstype;
        this.fare = fare;
    }
    
}
