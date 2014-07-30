import mpi.*;

public class Comm {
    public static void main(String[] args) throws MPIException {
        int[] buf = new int[1];
        int[] buf2 = new int[1];
        MPI.Init(args);

        int myrank = MPI.COMM_WORLD.getRank();
        int size = MPI.COMM_WORLD.getSize();

        buf[0] = myrank;
        if (myrank == 1){
            MPI.COMM_WORLD.send(buf, 1, MPI.INT, 0, 1);
            MPI.COMM_WORLD.recv(buf2, 1, MPI.INT, 0, 2);
        } else if (myrank == 0) {
            MPI.COMM_WORLD.recv(buf2, 1, MPI.INT, 1, 1);
            MPI.COMM_WORLD.send(buf, 1, MPI.INT, 1, 2);
        }

        System.out.println("Hello, I am " + buf[0] + " and I recv " + buf2[0]);
        MPI.Finalize();

    }
}
