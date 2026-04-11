public class Phone extends Hardware{
    
    private Phone(Builder builder){
        super(builder);
    }
    @Override
    public String unit(){
        return getSpec() + " Megapixels";
    }
    
    public static class Builder extends Hardware.Builder {
        @Override
        public Phone build() {
            return new Phone(this);
        }
    }
}