# FinalProject
El juego constara de que un personaje manejado con las flechas del teclado por un usuario logre atrapar las 6 gemas del infinito que iran saliendo de la parte superior de la ventana, el movimiento del personaje es manejado con un hilo igual que el movimiento de las gemas. El usuario ganara en el momento en que logre atrapar las 6 gemas. Tambien caeran trampas que disminuiran la vida del personaje, en el momento en que la vida del personaje sea 0 finalizara la partida, el movimiento de las trampas dera manejado con un hilo. Las trampas son de dos tipos que se manejaran con herencia, un tipo es bomba y el otro electricidad. Para jugar se debe registra un usuario el cual debe contar con un minimo de 4 caracteres, el programa no permite registrar un usuario con el mismo nombre de uno ya creado, cada usuario registrado se ira almacenando en un ArrayList.

El usuaio tendra la opcion de escoger un personaje con el cual jugar y un campo en el cual jugar, los personajes que estaran disponibles seran leidos desde un archivo .txt que esta seprado por ";" que contien toda la informacion de los personajes, estos seran guaradados en una lista doblemente enlazada. Los campos disponibles se manejaran de la misma manera.

El campo tendra las gemas que el personaje atrapara, las gemas son almacenadas en un arbol binario de busqueda que estara organizado por el poder que tiene la gema. El peronaje tambien tendra un arbol binario de busqueda de gemas en que se iran guardando las gemas que el personaje vaya atrapando. El campo tendra un cronometro que maracara el tiempo que esta empleando el usuario en el juego. El cronometro sera manejado con un hilo propio.

El programa tambien permite buscar a un jugador por su nombre, en caso de que no exista se manda una excepcion. Tambien permite buscar un personaje por su nombre, en caso de que exista se manda un excepcion. 

El programa tambien permite ver la lista de usuararios, la cual puede ser ordenada por untaje, nombre, mejor partida(Determinada por el menor tiempo) y tiempo acumulado de jeugo.

Existe un interfaz que contiene el metodo de movimiento, los limites del eje X y Y de la ventana, la rapidez con la que se movera el personaje, trampa y gema.

El programa tambien permite guardar el progreso del usuario para que en el momento de volver a ejecutar el juego no tenga necesidad de volverse a registrar y pueda reaundar su partida.
