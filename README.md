# JKShop
專案開發環境
Android Studio Electric Eel | 2022.1.1 Patch 1
compileSDK：33
Build gradle 版本：7.0.2
kotlin-gradle-plugin 版本：1.5.0

專案內容
1. 本專案開發使用 MVVM 架構
2. 使用 Koin DI
3. 使用 Andrid JetPack Room DB 作為本地資料儲存
4. 使用 RX 來達成 DB 運作時的 Thread 切換（因 Room 進行 CRUD 時不能在 MainThread 上進行）

DAO 層，一種 DataSource，用於定義 DB 相關的 CRUD
依功能建立分為：
UserDao
ShopItemDao
OrderDao

JKOShopDatabase：一個繼承 RoomDataBase 的 抽象類別（官方定義）
此類別需要做一些基礎設定及定義，使 RoomDataBase 能夠完整運作
如在內部透過定義 annotation 定義資料表（Entity），
裡面定義取得 Dao 的 function，並透過 Room.databaseBuilder().build() 創建DB
用以取得這些 DAO 來進行 DB CRUD


a. 登入頁（LoginActivity）
提供 user 輸入名稱，按下登入後會從 DB 尋找 user 資料，
有資料則直接登入進主頁，如果沒有找到就 DB Insert User，然後進入主頁
並且記下當前的 User，下次開啟 APP 可直接跳過登入頁進入主頁

b. 主頁面：商品列表頁(JkoShopListActivity)
JKOShop 的商品列表頁，本頁使用 RecyclerView 呈現，
列表資料透過 DB 以分頁的方式查詢，每頁 15 筆，每次下滑 loadData
首次使用 APP 時，會先預載商品資料 Insert 進 DB 中，之後再開啟 APP 後就不會在 Insert，而是直接從 DB 內撈資料

ToolBar Right Icon：點擊後進入 我的頁面（MyInfoActivity）
查看購物車 Button：點擊後進入 購物車頁面（JkoShopCartActivity）
RecyclerView Item : 點擊後進入 商品資訊頁面（JkoShopDetailActivity）

c. 商品資訊頁 (JkoShopDetailActivity)
本頁顯示商品內的詳細資訊，透過列表頁傳入的商品ID，來讓 DB 取得該商品的詳細資訊
並提供兩個 Button
加入購物車：點擊後會 DB Insert 購買的UserName，以及該商品ID，然後返回列表頁
直接購買：點擊後將該商品的所有資訊帶到 確認訂單頁面（JkoShopOrderConfirmActivity）

d. 購物車頁面（JkoShopCartActivity）
本頁 User 加入購物車的所有商品列表，DB 透過 UserName 尋找購物車的所有商品
提供 checkBox 勾選要購買的商品，以及提供結帳 Button
結帳：點擊後，將欲購買的商品的所有資訊帶到 確認訂單頁面（JkoShopOrderConfirmActivity）

e. 確認訂單頁面（JkoShopOrderConfirmActivity）
本頁將購物車結帳以及詳細頁直接購買傳入的商品資訊都呈現在此頁，並且計算商品總額
提供 提交訂單 Button
提交訂單：點擊後，將 UserName 以及購買的商品列表 Insert 進 DB，完成購買
並將原先儲存於 DB 該 User 的購物車相關的商品資料一併清除

f. 我的頁面（MyInfoActivity）
此頁呈現 用戶名稱，以及該用戶所擁有的訂單歷史紀錄
透過 DB 查詢出該 User 的歷史訂單記錄並使用 RecyclerView 顯示
每個訂單記錄提供 刪除訂單 Button
刪除訂單：點擊後，透過該商品ID進行 DB Delete，來刪除該紀錄，並重新使用 DB 查詢紀錄
本頁也提供 登出按鈕
登出：點擊後 finish 所有的 Activity，並回到登入頁（LoginActivity）

