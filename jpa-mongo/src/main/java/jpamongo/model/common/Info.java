package jpamongo.model.common;

public class Info {
	private static String code;
	private static String message;
	public static String getCode() {
		return code;
	}
	public static void setCode(String code) {
		Info.code = code;
	}
	public static String getMessage() {
		return message;
	}
	public static void setMessage(String message) {
		Info.message = message;
	}
	public static void remoteError(String error) {
		Info.code = Code.remoteError;
		Info.message = String.format("remote error: %s", error);
	}
	public static void ok() {
		Info.code = Code.ok;
	}
}
