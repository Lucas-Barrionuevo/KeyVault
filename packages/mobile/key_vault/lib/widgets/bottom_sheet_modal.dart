import 'package:flutter/material.dart';
import 'package:key_vault/utils/sizes.dart';
import 'package:key_vault/widgets/widgets.dart';

class BottomSheetModal extends StatelessWidget {
  const BottomSheetModal({
    Key? key,
  }) : super(key: key);

  @override
  Widget build(BuildContext context) {
    Sizes(context);
    return Container(
        height: Sizes.scaleVertical * 45,
        decoration: BoxDecoration(
          borderRadius: BorderRadius.circular(30),
          border: Border.all(color: Colors.black12, width: 2),
          color: Colors.white,
        ),
        child: Column(
          children: [
            SizedBox(
              height: Sizes.scaleVertical * 3,
            ),
            CircleAvatar(
              radius: Sizes.scaleVertical * 3,
              child: Icon(Icons.reddit, size: Sizes.scaleVertical * 5),
            ),
            const Text(
              "Probando",
              style: TextStyle(fontSize: 30, fontWeight: FontWeight.w500),
            ),
            const Text(
              "Agregada hace 5 min",
              style: TextStyle(
                  fontSize: 17,
                  fontWeight: FontWeight.normal,
                  color: Colors.black45),
            ),
            SizedBox(
              height: Sizes.scaleVertical * 2,
            ),
            Padding(
              padding:
                  EdgeInsets.symmetric(horizontal: Sizes.scaleHorizontal * 3),
              child: Container(
                width: double.infinity,
                height: Sizes.scaleVertical * 8,
                padding: EdgeInsets.symmetric(
                    vertical: Sizes.scaleVertical,
                    horizontal: Sizes.scaleHorizontal * 5),
                decoration: BoxDecoration(
                    color: const Color.fromRGBO(0, 0, 0, 0.05),
                    borderRadius: BorderRadius.circular(10)),
                child: const FittedBox(
                  child: Text(
                    "Esta es mi contraseña",
                    style: TextStyle(fontWeight: FontWeight.bold, fontSize: 30),
                  ),
                ),
              ),
            ),
            SizedBox(
              height: Sizes.scaleVertical * 2,
            ),
            Padding(
              padding:
                  EdgeInsets.symmetric(horizontal: Sizes.scaleHorizontal * 20),
              child: const SubmitButton(title: "Copiar"),
            ),
            SizedBox(
              height: Sizes.scaleVertical,
            ),
            TextButton(onPressed: () {}, child: const Text("Eliminar"))
          ],
        ));
  }
}
