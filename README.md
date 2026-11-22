Per ensenyar la llista de productes a la pantalla fem servir un RecyclerView que funciona gracies a la classe ProducteAdapter.
Dins d'aquest mateix arxiu tenim el ProductoViewHolder, que basicament s'encarrega de guardar el elements visuals de cada targeta
com el nom del producte, el preu, les proteines, les calorias i els botons d'editar i esborrar perque l'aplicacio,
no els hagi d'anar buscant tota l'estona. Despres el ProductAdapter va agafa la llista de productes crea el disseny
de cada fila amb el onCreateViewHolder i va omplint cada targeta amb les seves dades fent servir el onbindViewHolder.
Hem programat el quue pasa quan cliques els botons d'editar o esborrar de cada producte, que de moment treuen un missatge rapid a la pantalla
amb un toast i ho deixen apuntat al logcat per comprovar que funciona

EL que hem fet per progamar el menu i moure'ns entre les diferents pantalles de l'app es utilitzan el Intent y el botons amb el setonClickListener.
A la pantalla d'inici de sessio, si utlitzas els diferents botons que apareixen vas a una pantalla diferent,
per exemple la de inici de sessió has de posa el teu mail y contrasenya per entrar en canvi quan entres como a convidat 
entres d'una, quan li dones a registrarte et surt la pantalla per que puguis posar les teves dades com el nom els cognoms i els 
mail que vulgis utilitzar, totes les comprovacions es fan amb if senzills per exemple que no hi hagi cap camp en blanc
o que les dos contraseñas siguin iguals.
