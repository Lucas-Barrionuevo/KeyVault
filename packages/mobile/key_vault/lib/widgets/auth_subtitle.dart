import 'package:flutter/material.dart';

class AuthSubtitle extends StatelessWidget {
  const AuthSubtitle({
    Key? key,
  }) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return Container(
      padding: const EdgeInsets.only(top: 2),
      child: const Text(
        'Manten tus datos seguros',
        style: TextStyle(
            fontSize: 18, fontWeight: FontWeight.w300, color: Colors.black87),
      ),
    );
  }
}
