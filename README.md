# MacroSuper - Activitat 06 (Menú i Llista)

## 1. Pantalla inicial (Splash Screen)
Hem creat una activitat inicial (`SplashActivity`) configurada com a pantalla d'arrencada al `AndroidManifest.xml`. Mostra el logotip vectorial de l'aplicació al centre amb una animació d'aparició i escalat (canviant `alpha`, `scaleX` i `scaleY`). Passats 3 segons, utilitzem un `Handler` per navegar automàticament a la pantalla següent i tanquem l'activitat amb `finish()` perquè l'usuari no hi pugui tornar enrere.

## 2. Menús de navegació
Per moure'ns per l'aplicació hem combinat dos tipus de navegació:

* **Navegació entre pantalles principals (Menú inferior i lateral):** Hem implementat un *Bottom Navigation* i un *Navigation Drawer* (desplegable des de la barra superior) vinculats a diferents `Fragments` (`Llista`, `Preferències` i `Perfil`). D'aquesta manera, l'usuari pot canviar de secció fàcilment des de qualsevol punt.
* **Navegació d'accés (Login i Registre):** A les pantalles d'inici de sessió i registre fem servir `Intent` i `setOnClickListener` als botons per passar d'una activitat a una altra (entrar amb compte, accedir com a convidat o anar al registre), amb comprovacions bàsiques amb `if` per validar camps buits o contrasenyes coincidents.

## 3. Llista dinàmica (RecyclerView, Adapter i ViewHolder)
Per mostrar els productes de prova fem servir un `RecyclerView` dins del `HomeFragment`, que treballa amb la *data class* `Producto` i la classe `ProductoAdapter`:

* **`ProductoViewHolder`:** Guarda les referències als elements visuals de cada targeta (`item_producto.xml`), com el nom del producte, el preu, les proteïnes, les calories i els botons d'editar i esborrar. Això evita que l'app hagi de buscar les vistes constantment i millora el rendiment.
* **`ProductoAdapter`:** S'encarrega de gestionar la llista de dades. Crea el disseny de cada fila amb `onCreateViewHolder`, omple cada targeta amb la seva informació a `onBindViewHolder` i indica quants elements hi ha amb `getItemCount`.
* **Events programats:** Hem programat la interacció dels botons **Editar** i **Esborrar** dins l'`Adapter`, i el botó flotant **Afegir (`+`)** al `HomeFragment`. De moment, totes tres accions mostren un avís immediat a l'usuari amb un `Toast` i deixen un registre al `Logcat` per verificar-ne el funcionament.

## 4. Usabilitat i Accessibilitat
Durant el desenvolupament hem aplicat diverses mesures per fer l'aplicació més còmoda i accessible:

* **Mida tàctil adequada:** Els botons i camps interactius tenen una alçada mínima de `48dp` (`android:minHeight="48dp"`) per facilitar-ne la pulsació a persones amb dificultats motrius o poca precisió.
* **Descripcions per a lectors de pantalla:** Hem afegit l'atribut `android:contentDescription` als elements gràfics i botons sense text (com el logotip del Splash Screen i el botó flotant `+`).
* **Alt contrast visual:** Utilitzem un tema fosc combinat amb textos blancs i colors destacats (verd i taronja) per garantir una lectura clara.
* **Feedback immediat i navegació simple:** Els *sliders* de proteïnes i calories actualitzen el seu valor numèric en temps real en moure'ls, els botons d'acció responen amb missatges `Toast` i l'usuari pot arribar a qualsevol secció principal en un sol clic des del menú.
