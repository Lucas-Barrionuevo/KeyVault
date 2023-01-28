import 'package:flutter/material.dart';
import 'package:flutter/services.dart';
import 'package:key_vault/services/password_service.dart';
import 'package:key_vault/utils/sizes.dart';
import 'package:key_vault/widgets/widgets.dart';
import 'package:provider/provider.dart';
import 'package:url_launcher/url_launcher.dart';

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
        height: Sizes.scaleVertical * 43,
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
    goBack() {
      Navigator.of(context).pop();
    }

    Future<void> launchExplorerWithUrl(String url) async {
      if (url == "") return;
      final parsedUrl = Uri.parse(url);
      try {
        await launchUrl(parsedUrl);
      } catch (e) {
        // ignore: avoid_print
        print(e);
      }
    }

    return Stack(children: [
      Column(
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
          GestureDetector(
            onTap: () {
              launchExplorerWithUrl(password.url ?? "");
            },
            child: Text(
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
              child: FittedBox(
                child: Text(
                  password.content ?? "",
                  style: const TextStyle(
                      fontWeight: FontWeight.bold, fontSize: 30),
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
            child: SubmitButton(
              title: "Copiar",
              onPressed: () {
                Clipboard.setData(ClipboardData(text: password.content));
                final snackBar = SnackBar(
                  content: const Text('Yay! A SnackBar!'),
                  action: SnackBarAction(
                    label: 'Undo',
                    onPressed: () {
                      // Some code to undo the change.
                    },
                  ),
                );

                // Find the ScaffoldMessenger in the widget tree
                // and use it to show a SnackBar.
                ScaffoldMessenger.of(context).showSnackBar(snackBar);
              },
            ),
          ),
          SizedBox(
            height: Sizes.scaleVertical,
          ),
        ],
      ),
      Positioned(
          right: Sizes.scaleHorizontal * 5,
          top: Sizes.scaleVertical * 2,
          child: GestureDetector(
            onTap: () async {
              final error =
                  await Provider.of<PasswordService>(context, listen: false)
                      .deletePassword('${password.id}');
              if (error == null) goBack();
            },
            child: Icon(
              size: Sizes.scaleVertical * 3.5,
              Icons.delete_forever,
              color: Colors.red.withAlpha(200),
            ),
          ))
    ]);
  }
}
