import CommonCrypto
import ComposeApp
import CryptoKit
import Foundation
import Security

// KeystoreManager is the Kotlin interface
class KeyManagementIOS: KeyManagement<Data?> {
    func saveKeyToKeychain(key: Data) -> Bool {
        let query: [String: Any] = [
            kSecClass as String: kSecClassKey,
            kSecAttrApplicationTag as String: keyAlias,
            kSecValueData as String: key,
            kSecAttrKeyType as String: kSecAttrKeyTypeAES,
            kSecAttrKeySizeInBits as String: 256,
        ]

        SecItemDelete(query as CFDictionary)
        let status = SecItemAdd(query as CFDictionary, nil)

        return status == errSecSuccess
    }

    func create() -> Data? {
        var keyData = Data(count: kCCKeySizeAES256)
        let result = keyData.withUnsafeMutableBytes { bytes in
            SecRandomCopyBytes(
                kSecRandomDefault, kCCKeySizeAES256, bytes.baseAddress!)
        }
        
        saveKeyToKeychain(keyData)

        return result == errSecSuccess ? keyData : nil
    }
    
    func get() -> Data? {
        if let existingKey = getKeyFromKeychain() {
            return existingKey
        }

        if let newKey = createKey() {
            _ = saveKeyToKeychain(key: newKey)
            return newKey
        }

        return nil
    }

    func getKeyFromKeychain() -> Data? {
        let query: [String: Any] = [
            kSecClass as String: kSecClassKey,
            kSecAttrApplicationTag as String: keyAlias,
            kSecReturnData as String: kCFBooleanTrue!,
            kSecMatchLimit as String: kSecMatchLimitOne,
        ]

        var result: AnyObject?
        let status = SecItemCopyMatching(query as CFDictionary, &result)

        if status == errSecSuccess, let keyData = result as? Data {
            return keyData
        }

        return nil
    }

}
