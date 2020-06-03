package kai;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

public class Client {
    Socket sok = null;              // �ڑ��pSocket
    BufferedReader in = null;       // ��͗p�X�g���[��
    OutputStreamWriter out = null;  // �o�͗p�X�g���[��

    public Client() {
    }

    public void connectServer(String host, int port) throws Exception {
	sok = new Socket(host, port);
	// �\�P�b�g�����o�̓X�g���[���𓾂�
        in = new BufferedReader(
                new InputStreamReader(sok.getInputStream()));
        out = new OutputStreamWriter(sok.getOutputStream());
    }

    // ���b�Z�[�W���M
    public void write(String Message) throws Exception{
        out.write(Message + "\n");
	out.flush();
    }

    // ���b�Z�[�W��M
    public String read() throws Exception{
        String readString = null;
        readString = in.readLine();

        return readString;
    }

    // �ڑ���ؒf����
    public void close() throws Exception{
        sok.close();    // �\�P�b�g�����
        sok = null;
    }
}
