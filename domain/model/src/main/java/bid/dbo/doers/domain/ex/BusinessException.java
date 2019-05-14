package bid.dbo.doers.domain.ex;

import java.util.function.Supplier;

public class BusinessException extends ApplicationException {

    private final Type type;

    public BusinessException(Type type){
        super(type.message);
        this.type = type;
    }

    @Override
    public String getCode(){
        return type.name();
    }

    public enum Type {
        EX1("Msg"),
        EX2("Msg");

        private final String message;

        Type(String message) {
            this.message = message;
        }

        public String getMessage() {
            return message;
        }

        public BusinessException build() {
            return new BusinessException(this);
        }

        public Supplier<Throwable> defer() {
            return () -> new BusinessException(this);
        }

    }


}
