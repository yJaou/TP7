package champollion;

public class ServicePrevu {

    private int volumeCM;
    private int volumeTD;
    private int volumeTP;
    private UE ue;

    public ServicePrevu(int volumeCM, int volumeTD, int volumeTP, UE ue) {
        this.volumeCM = volumeCM;
        this.volumeTD = volumeTD;
        this.volumeTP = volumeTP;
        this.ue = ue;
    }

    public int getServiceType(TypeIntervention type) {
        int volume = 0;
        if (type.equals(TypeIntervention.CM)){
            volume += this.getVolumeCM();
        }
        if (type.equals(TypeIntervention.TD)){
            volume += this.getVolumeTD();
        }
        if (type.equals(TypeIntervention.TP)){
            volume += this.getVolumeTP();
        }
        return volume;
    }

    public int getVolumeCM() {
        return volumeCM;
    }

    public int getVolumeTD() {
        return volumeTD;
    }

    public int getVolumeTP() {
        return volumeTP;
    }

    public UE getUe() {
        return ue;
    }

    public void addToCM(int toAdd){
        volumeCM += toAdd;
    }

    public void addToTD(int toAdd){
        volumeTD += toAdd;
    }

    public void addToTP(int toAdd){
        volumeTP += toAdd;
    }
}
