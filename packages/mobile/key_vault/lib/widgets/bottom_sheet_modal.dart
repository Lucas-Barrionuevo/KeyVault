import 'package:flutter/material.dart';
import 'package:key_vault/services/password_service.dart';
import 'package:key_vault/utils/sizes.dart';
import 'package:key_vault/widgets/widgets.dart';
import 'package:provider/provider.dart';

class BottomSheetModal extends StatelessWidget {
  final String id;
  const BottomSheetModal({
    Key? key,
    required this.id,
  }) : super(key: key);

  @override
  Widget build(BuildContext context) {
    Sizes(context);

    final password = Provider.of<PasswordService>(context, listen: false);
    return FutureBuilder(
        future: password.loadPassword(id),
        builder: (context, snapshot) {
          if (snapshot.hasData) {
            return const _Content();
          } else {
            return const _Content(isLoading: true);
          }
        });
  }
}

class _Content extends StatelessWidget {
  final bool isLoading;
  const _Content({Key? key, this.isLoading = false}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return Container(
        height: Sizes.scaleVertical * 48,
        decoration: BoxDecoration(
          borderRadius: BorderRadius.circular(30),
          border: Border.all(color: Colors.black12, width: 2),
          color: Colors.white,
        ),
        child: isLoading ? const Loading() : const _BottomSheetContent());
  }
}

class _BottomSheetContent extends StatelessWidget {
  const _BottomSheetContent({
    Key? key,
  }) : super(key: key);

  @override
  Widget build(BuildContext context) {
    final password = Provider.of<PasswordService>(context).selectedPassword;
    return Column(
      children: [
        SizedBox(
          height: Sizes.scaleVertical * 3,
        ),
        PasswordIcon(
          password: password,
        ),
        Padding(
          padding: EdgeInsets.symmetric(
            horizontal: Sizes.scaleHorizontal * 5,
          ),
        ),
        Text(
          password.userOrMail ?? "",
          textAlign: TextAlign.center,
          maxLines: 1,
          overflow: TextOverflow.ellipsis,
          style: const TextStyle(fontSize: 17, color: Colors.black45),
        ),
        Text(
          password.url ?? "",
          textAlign: TextAlign.center,
          maxLines: 1,
          overflow: TextOverflow.ellipsis,
          style: const TextStyle(
              fontSize: 17,
              decoration: TextDecoration.underline,
              fontWeight: FontWeight.normal,
              color: Colors.black45),
        ),
        SizedBox(
          height: Sizes.scaleVertical * 2,
        ),
        Padding(
          padding: EdgeInsets.symmetric(horizontal: Sizes.scaleHorizontal * 3),
          child: Container(
            width: double.infinity,
            height: Sizes.scaleVertical * 8,
            padding: EdgeInsets.symmetric(
                vertical: Sizes.scaleVertical,
                horizontal: Sizes.scaleHorizontal * 5),
            decoration: BoxDecoration(
                color: const Color.fromRGBO(0, 0, 0, 0.05),
                borderRadius: BorderRadius.circular(10)),
            child: FittedBox(
              child: Text(
                password.content ?? "",
                style:
                    const TextStyle(fontWeight: FontWeight.bold, fontSize: 30),
              ),
            ),
          ),
        ),
        SizedBox(
          height: Sizes.scaleVertical * 2,
        ),
        Padding(
          padding: EdgeInsets.symmetric(horizontal: Sizes.scaleHorizontal * 20),
          child: const SubmitButton(title: "Copiar"),
        ),
        SizedBox(
          height: Sizes.scaleVertical,
        ),
        TextButton(onPressed: () {}, child: const Text("Eliminar"))
      ],
    );
  }
}
