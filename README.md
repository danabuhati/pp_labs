<h1> Teme </h1>


<h3>Laborator 3</h3>
<h4>tema 1</h4>
<p>ADT-urile uitilizate sunt 2 clase, una conține elementele reprezentative ale unui articol (titlu, descriere, dată, linkul aferent), cealaltă, <code>feed</code>, reprezintă întreg feed-ul procesat și e formată din 
  titlul principal și o listă de clase <code>feed_item</code>. Folosind <i>JSoup</i> am extras conținutul găsit la tag și am construit clasele necesare.</p>

<h4>tema 2</h4>
<p>Aici am decis să detectez condițiile necesare folosind expresii regulate, prima găsește numărul paginii, pornind de la ideea că are formatul <code>[minim 2 spatii] [cel putin o cifră] [din nou minim 2 spatii]</code>, să se facă diferența cu situația când
în text e simplu menționat un număr între câte un spațiu. A doua expresie e utilizată pentru a detecta spații multiple, aici se includ orice fel de tab-uri și newline-uri.
După am utilizat metoda <code>replace</code> înlocuid secvențele detectate cu un string gol. Intrarea e preluată din fișierul <i>book.txt</i>, găsit în proiect și care conține un exemplu de text, iar textul redactat se plasează în <i>out.txt</i></p>

<h3>Laborator 4</h3>
<h4>tema 1</h4>
<p>Programul folosește librăria json pentru a parsa fișiere de tip json, JSoup pentru fișiere xml și SnakeYAML pentru fișiere yaml. În linii generale se folosește okhttp pentru a obține conținutul aflat la url-ul specificat, după care se invocă funcția necesară. Rezultatul e păstrat sub formă de map, în care datele sunt grupate în funcție de "tag"-ul din care provin.</p>
