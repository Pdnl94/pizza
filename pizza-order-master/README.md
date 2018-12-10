# Budget

### 1. Követelményanalízis
#### 1.1 Célkitűzés
Az alkalmazás célja, hogy a regisztrált felhasználók számára egy olyan felületet nyújtson, ahol egyszerűen nyomon követhetik a bevételeiket, illetve fizetményeiket. A bejelentkezett felhasználók rögzíthetik bevételeiket, fizetményeiket, megjegyzéseket írhatnak hozzájuk, illetve több szempont alapján szűrhetik kiadásaikat (szolgáltató, időpont).
#### Funkcionális elvárások
- Vendégként
  - regisztrálhatok az oldalra,
  - bejelentkezhetek az oldalra,
  - láthatok statisztikákat az oldalról (összesített bevétel/fizetmény, felhasználók száma).
- Felhasználóként
  - rögzíthetek új bevételt, illetve kiadást,
  - láthatom a vagyoni helyzetemet,
  - módosíthatom az általam rögzített bevételeket, illetve fizetményeket,
  - törölhetem az általam rögzített bevételeket, illetve fizetményeket,
  - megtekinthetem az összes általam hozzáadott bevételt/fizetményt,
  - kereshetek a bevételeim/fizetményeim között időpont, vagy szolgáltató alapján.
- Adminisztrátorként
  - rögzíthetek új bevételt, illetve fizetményt bármely felhasználó számára,
  - módosíthatom bármely felhasználó bevételeit, illetve fizetményeit,
  - törölhetem bármely felhasználó bevételeit, illetve fizetményeit,
  - megtekinthetem a felhasználók által rögzített összes bevételt, fizetményeit,
  - kereshetek a bevételek, illetve kiadások között időpont, szolgáltató vagy felhasználó alapján,
  - hozzáadhatok, módosíthatok és archiválhatok a fizetményekhez rögzíthető szolgáltatók, illetve kategóriák közül.
#### Nem funkcionális elvárások
Az oldal legyen
- gyors,
- felhasználóbarát, ergonomikus elrendezésű,
- biztonságos: biztosítsa a megfelelő funkciókhoz a megfelelő hozzáférést jogosultságok alapján.
#### 1.2 Szakterületi fogalomjegyzék
- bevétel: fizetésből, vagy más forrásból származó pénzösszeg.
- fizetmény: termékért, vagy valamilyen szolgáltatásért cserébe kifizetett összeg.
- szolgáltató: olyan üzlet, vagy valamilyen szolgáltatást nyújtó cég, amelynek az árutermékekért, vagy más ellenszolgáltatásért cserébe pénzt adtunk át (online vagy személyesen), pl. Tesco, BKK, McDonalds, stb.
- kategóriák: az egyes kiadások tételeihez kapcsolódó kategóriák (pl. utazás - vonat/buszjegyhez, élelmiszer, tisztítószer, ruházat, stb.).
#### 1.3 Szerepkörök
- vendég: hozzáfér a főoldalhoz, beléphet és regisztrálhat, de ezen kívül az adatbázisban nem tud módosítást végezni.
- felhasználó: hozzáfér az általa rögzített bevételekhez és kiadásokhoz, újakat adhat hozzá, módosíthatja és törölheti a meglévőket.
- adminisztrátor: bármely felhasználó bármely rögzített bevételéhez és kiadásaihoz hozzáférhet, módosíthatja és törölheti azokat, továbbá a kiadásokhoz tartozó kategóriákat és szolgáltatókat hozhat létre, módosíthatja és archiválhatja azokat.

### Tervezett Végpontok:
User:
- GET /users: Minden felhasználót visszad, ha admin jogosultságú felhasználó kéri le.
- POST /users/register: Ha még nem létezik ilyen felhasználónévvel felhasználó, akkor a json body-ban megadott adatokkal létrehoz egy felhasználót.
- POST /users/login: A body-ban megadott felhasználót bejelentkezteti a rendszer.

Income:
- GET /incomes: admin jogosultsággal visszaadja az összes bevételt, egyéb esetben a felhasználóhoz tartozó bevételeket.
- GET /incomes/{id}: a megadott id-vel visszadja a bejövő pénzt, ha az az éppen bejelentkezett felhasználóhoz tartozik, aki admin is.
- POST /incomes: A body-ban megadott fizetményt elmenti.
- PUT /incomes/{id}: Az id-t megadva próbálja frissíteni az entitást, ha létezik a body-ban megadott fizetményt entitás alapján 
- DELETE /incomes/{id}: Az id paraméterbe megadott bejövő pénzt törli, amennyiben létezik.
- GET /incomes/user/{id}: az id paraméterben megadott usert megkeresi, és ha megtalálja és ADMIN jogosultságú felhasználó van éppen belépve, akkor visszaadja az összes hozzá tartozó bejövő pénzt.

Outlay:
- GET /outlay: Ha admin jogosultsággal rendelkezünk, akkor visszaadja az összes kifizetést, egyébként a felhasználóhoz tartozó kifizetéseket.
- GET /outlay/{id}: Megkeresi a paraméterben megadott id-jú kifizetést, és ha a hozzá tartozó felhasználó az éppen bejelentkezett felhasználó, vagy admin jogosultsággal rendelkezik, akkor visszaadja a kifizetést
- POST /outlay: A body-ban megadott kifizetést lementi 
- PUT /outlay/{id}: A megadott id-jú kifizetést frissíti, ha van ilyen kifizetést, és az aktuálisan bejelentkezett felhasználóhoz tartozik
- DELETE /outlay/{id}: A megadott id-jú kifizetést törli, ha van ilyen, és az éppen bejelentkezett felhasználóhoz tartozik
- GET /outlay/{id}/items: A megadott id-jú kifizetéshez visszaadja a hozzá tartozó tételeket, amennyiben az létezik.
- POST /outlay/{id}/items:  A paraméterben megadott id-jú kifizetéshez hozzáad egy tételt, amit body-ban adunk meg, ha létezik a kifizetés, és megegyezik a hozzá tartozó felhasználó az éppen bejelentkezettel, vagy admin jogsultsággal rendelkezik
- DELETE /outlay/{id}/items/{itemid}: Megkeresi az {id} id-jű kifizetést, majd a hozzá tartozó {itemid} id-jű tételt törli
- GET /outlay/user/{id}: ADMIN jogosultsággal megtekinthezjük a paraméterben megadott id-jű userhez tartozó kifizetéseket.

Provider:
- GET /provider: Szolgáltatók lekérése
- POST /provider: A Body-ban megadott szolgáltatót elmenti.
- PUT /provider/{id}: Frissíti a Body-ban megadott adatokkal az adott id-jú szolgáltatót.
- DELETE /provider/{id}: Törli az adott id-val rendelkező szolgáltatót.
- GET /provider/{id}: Ha létezik az adott id-val rendelkező szolgáltató, akkor annak az adatait küldi vissza, különben 404-es hibát ad.
- GET /provider/{id}/categories: Ha létezik az adott id-val rendelkező szolgáltató, akkor a hozzá tartozó kategóriákat adja vissza, ellenkező esetben 404-es hibát ad.
- POST /provider/{id}/categories: Ha létezik az adott id-val rendelkező szolgáltató, akkor a Body-ban megadott kategóriát hozzárendeli a szolgáltatóhoz, ha van ilyen (ha nincs, akkor előtte létrehozza).
- DELETE /provider/{id}/categories/{catid}: Ha létezik az adott id-val rendelkező szolgáltató, és neki az egyik kategóriája a {catid}-vel rendelkező kategória, akkor a szolgáltatótól törli a ketegóriát.

Categories:
- GET /categories: Visszaadja az összes termékkategóriát
- POST /categories: Elmenti a Body-ban megadott kategóriát.
- PUT /categories/{id}: Frissíti a Body-ban megadott adatokkal az adott id-jú kategóriát.
- DELETE /categories/{id}: Törli az adott id-val rendelkező kategóriát.
- GET /categories/{id}: Ha létezik termékkategória az adott id-val, akkor azt adja vissza, ellenkező esetben 404-es hibát ad.

#### Szekvenciadiagram
![Szekvenciadiagram](/src/main/resources/images/sequence_diagram.jpg)

#### Táblaszerkezet
![Táblaszerkezet](/src/main/resources/images/table.png)

#### Fejlesztői környezet
Az alkalmazás a Netbeans 8.2 verziójú IDE segítségével készült. Az API hívások Java nyelven íródtak, az oldal Angular keretrendszer használatával, a JavaScript és a PHP nyelveken kerültek megírásra.

### Könyvtárstruktúra

-src
  - main
      - java
          - hu
              - elte
                  - shoppinglist
                      - `ShoppinglistApplication.java`
                      - controllers
                          - `IncomeController.java`
                          - `ItemCategoryController.java`
                          - `ItemController.java`
                          - `ProviderController.java`
                          - `OutlayController.java`
                          - `UserController.java`
                      - entities
                          - `Income.java`
                          - `ItemCategory.java`
                          - `Item.java`
                          - `Provider.java`
                          - `Outlay.java`
                          - `User.java`
                      - repositories
                          - `IncomeRepository.java`
                          - `ItemCategoryRepository.java`
                          - `ItemRepository.java`
                          - `ProviderRepository.java`
                          - `OutlayRepository.java`
                          - `UserRepository.java`
                      - security
                          - `AuthenticatedUser.java`
                          - `MyUserDetailsService.java`
                          - `WebSecurityConfig.java`
      - resources
          - `data.sql`
          - `application.properties
- budget-client
  - src
    - app
      - category-list
        - `category-list.component.ts`
        - `category-list.component.html`
        - `category-list.component.spec.ts`
        - `category-list.component.ts`
      - income-delete
        - `income-delete.component.ts`
        - `income-delete.component.html`
        - `income-delete.component.spec.ts`
        - `income-delete.component.ts`
      - income-edit
        - `income-edit.component.ts`
        - `income-edit.component.html`
        - `income-edit.component.spec.ts`
        - `income-edit.component.ts`
      - income-form
        - `income-form.component.ts`
        - `income-form.component.html`
        - `income-form.component.spec.ts`
        - `income-form.component.ts`
      - login
        - `login.component.ts`
        - `login.component.html`
        - `login.component.spec.ts`
        - `login.component.ts`
      - main-page
        - `main-page.component.ts`
        - `main-page.component.html`
        - `main-page.component.spec.ts`
        - `main-page.component.ts`
      - outlay-delete
        - `outlay-delete.component.ts`
        - `outlay-delete.component.html`
        - `outlay-delete.component.spec.ts`
        - `outlay-delete.component.ts`
      - outlay-edit
        - `outlay-edit.component.ts`
        - `outlay-edit.component.html`
        - `outlay-edit.component.spec.ts`
        - `outlay-edit.component.ts`
      - outlay-form
        - `outlay-form.component.ts`
        - `outlay-form.component.html`
        - `outlay-form.component.spec.ts`
        - `outlay-form.component.ts`
      - outlays-list
        - `outlays-list.component.ts`
        - `outlays-list.component.html`
        - `outlays-list.component.spec.ts`
        - `outlays-list.component.ts`
      - provider-delete
        - `provider-delete.component.ts`
        - `provider-delete.component.html`
        - `provider-delete.component.spec.ts`
        - `provider-delete.component.ts`
      - provider-edit
        - `provider-edit.component.ts`
        - `provider-edit.component.html`
        - `provider-edit.component.spec.ts`
        - `provider-edit.component.ts`
      - provider-form
        - `provider-form.component.ts`
        - `provider-form.component.html`
        - `provider-form.component.spec.ts`
        - `provider-form.component.ts`
      - provider-list
        - `provider-list.component.ts`
        - `provider-list.component.html`
        - `provider-list.component.spec.ts`
        - `provider-list.component.ts`
      - registration
        - `registration.component.ts`
        - `registration.component.html`
        - `registration.component.spec.ts`
        - `registration.component.ts`
      - `app-routing.module.ts`
      - `app.component.css`
      - `app.component.html`
      - `app.component.spec.ts`
      - `app.component.ts`
      - `app.module.ts`
      - `auth.guard.spec.ts`
      - `auth.service.spec.ts`
      - `auth.service.ts`
      - `income.service.spec.ts`
      - `income.service.ts`
      - `income.ts`
      - `itemcategory.ts`
      - `outlay.service.spec.ts`
      - `outlay.service.ts`
      - `provider.ts`
      - `user.ts`
