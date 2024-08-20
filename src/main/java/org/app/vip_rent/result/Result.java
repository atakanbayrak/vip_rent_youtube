package org.app.vip_rent.result;

public class Result {
    private final int resultCode;
    private final String resultText;

    public Result(int resultCode, String resultText) {
        this.resultCode = resultCode;
        this.resultText = resultText;
    }

    public static final Result SUCCESS = new Result(0,"OK!");
    public static final Result SERVER_ERROR = new Result(1,"SERVER ERROR!");

    public Result(Result result) {
        this.resultCode = result.resultCode;
        this.resultText = result.resultText;
    }

    public int getResultCode(){return resultCode;}

    public String getResultText(){return resultText;}

    public static Result showMessage(Result resultType, String messageText) {
        Result result = new Result(resultType);
        return result;
    }
}
