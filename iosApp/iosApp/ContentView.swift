import ComposeApp
import SwiftUI
import UIKit

struct ComposeView: UIViewControllerRepresentable {
    func makeUIViewController(context: Context) -> UIViewController {
        MainViewControllerKt.MainViewController()
    }

    func updateUIViewController(
        _ uiViewController: UIViewController, context: Context
    ) {}

    func makeCoordinator() -> ComposeCoordinator {
        return ComposeCoordinator()
    }
}

struct ContentView: View {
    var body: some View {
        ComposeView().ignoresSafeArea(edges: .all)
    }
}

class ComposeCoordinator {
    init() {
        DependencyInjectionKt.doInitDependencyFramework(
            appComponent: IosApplicationComponent(
                keyManagement: KeyManagementIOS()
            )
        )
    }
}
