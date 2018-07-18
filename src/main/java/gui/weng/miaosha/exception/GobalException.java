package gui.weng.miaosha.exception;

import gui.weng.miaosha.result.CodeMsg;

public class GobalException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    private CodeMsg cm;

    public GobalException(CodeMsg cm){
        super(cm.toString());
        this.cm=cm;
    }

    public CodeMsg getCm() {
        return cm;
    }
}
