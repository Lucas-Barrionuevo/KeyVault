import 'package:flutter/material.dart';
import 'package:key_vault/screens/screens.dart';
import 'package:key_vault/services/services.dart';
import 'package:key_vault/widgets/widgets.dart';
import 'package:provider/provider.dart';

class CheckAuthScreen extends StatelessWidget {
  const CheckAuthScreen({super.key});

  @override
  Widget build(BuildContext context) {
    final authService = Provider.of<AuthService>(context, listen: false);

    return Scaffold(
      body: Center(
        child: FutureBuilder<bool>(
          future: authService.loginWithToken(),
          builder: (BuildContext context, AsyncSnapshot<bool> snapshot) {
            if (!snapshot.hasData) {
              return const Loading();
            }
            final isAuthorized = snapshot.data;
            if (isAuthorized == false) {
              unauthorizedRedirect(context);
            } else {
              authorizedRedirect(context);
            }

            return Container();
          },
        ),
      ),
    );
  }

  Future<void> authorizedRedirect(BuildContext context) {
    return Future.microtask(() {
      Navigator.pushReplacement(
          context,
          PageRouteBuilder(
              pageBuilder: (_, __, ___) => const MainBottomNavScreen(),
              transitionDuration: const Duration(seconds: 0)));
    });
  }

  Future<void> unauthorizedRedirect(BuildContext context) {
    return Future.microtask(() {
      Navigator.pushReplacement(
          context,
          PageRouteBuilder(
              pageBuilder: (_, __, ___) => const LoginScreen(),
              transitionDuration: const Duration(seconds: 0)));
    });
  }
}
