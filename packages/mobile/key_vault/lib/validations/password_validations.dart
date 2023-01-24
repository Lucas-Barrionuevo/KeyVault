const nameMaxLength = 30;
const usernameMaxLength = 40;
const urlMaxLength = 100;
const passwordMaxLength = 150;
const categoryMaxLength = 50;

class PasswordValidations {
  static name(value) {
    if (value != null && value.length > nameMaxLength) {
      return "Máximo $nameMaxLength caracteres";
    }
    if (value == "") {
      return "El nombre es requerido";
    }
    return null;
  }

  static username(value) {
    if (value != null && value.length > usernameMaxLength) {
      return "Máximo $usernameMaxLength caracteres";
    }
    return null;
  }

  static url(value) {
    if (value != null && value.length > urlMaxLength) {
      return "Máximo $urlMaxLength caracteres";
    }
    return null;
  }

  static password(value) {
    if (value == "") {
      return "La contraseña es requerida";
    }
    if (value.length > passwordMaxLength) {
      return "Máximo $passwordMaxLength caracteres";
    }
    return null;
  }

  static category(value) {
    if (value != null && value.length > categoryMaxLength) {
      return "Máximo $categoryMaxLength caracteres";
    }
    return null;
  }
}
