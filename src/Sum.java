/**
 * Created by Robbykunsan on 2014/07/31.
 */
import mpi.*;

public class Sum {
    public static void main(String[] args) throws MPIException {
        int NMAX = 12;
        int[] array = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12};
        int[] recv_buf = new int[1];
        int[] send_buf = new int[1];
        send_buf[0] = 0;

        MPI.Init(args);

        int myrank = MPI.COMM_WORLD.getRank();
        int size = MPI.COMM_WORLD.getSize();

        int ista = myrank * (NMAX/size);
        int iend = ista + (NMAX/size - 1);

        for (int i = ista; i <= iend; i++){
            send_buf[0] = send_buf[0] + array[i];
        }

        MPI.COMM_WORLD.reduce(send_buf, recv_buf, 1, MPI.INT, MPI.SUM, 0);

        if (myrank == 0) {
            System.out.println("SUM : " + recv_buf[0]);
        }
        MPI.Finalize();
    }
}
