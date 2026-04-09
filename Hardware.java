import java.util.Scanner;
public class Hardware {
    protected final String brand;
    protected final int spec;
    protected final String type;
    
    protected Hardware(Builder builder){
        this.brand = builder.brand;
        this.spec = builder.spec;
        this.type = builder.type;
    }
    
    public String getBrand(){
        return brand;
    }
    public int getSpec(){
        return spec;
    }
    public String getType(){
        return type;
    }
    
    public static class Builder{
        private String brand;
        private int spec;
        private String type;
        
        public Builder setBrand(String brand){
            this.brand = brand;
            return this;
        }
        public Builder setSpec(int spec){
            this.spec = spec;
            return this;
        }
        public Builder setType(String type){
            this.type = type;
            return this;
        }
        public Hardware build(){
            return new Hardware(this);
        }
    }
    
    public String unit(){
        return getSpec() + "Unknown unit";
    }
    
}