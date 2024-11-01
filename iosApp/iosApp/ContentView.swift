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
}
	
struct ContentView: View {
    var body: some View {
        ComposeView().ignoresSafeArea()
    }
}
			
