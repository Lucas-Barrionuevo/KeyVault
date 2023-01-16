import 'package:http/http.dart';

class ServicesUtils {
  static bool isOk(Response resp) {
    if (resp.statusCode.toString().startsWith("2")) return true;
    return false;
  }
}
