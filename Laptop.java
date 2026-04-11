public class Laptop extends Hardware {
    
    private Laptop(Builder builder){
        super(builder);
    }
    @Override
    public String unit(){
        return getSpec() + "GB RAM";
    }
    
    public static class Builder extends Hardware.Builder {
        @Override
        public Laptop build() {
            return new Laptop(this);
        }
    }