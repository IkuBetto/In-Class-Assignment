package kai;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.ss.util.CellUtil;

public class excel {

    public excel(String index,String name,String Numberofpeople,String time,String id) throws IOException {
    	int time_int=Integer.parseInt(time);
    	int i = Integer.parseInt(index);
        // 変更元を取込
        FileInputStream in
            = new FileInputStream("C:\\Users\\74160\\Desktop\\pleiades-e4.2-java-jre_20130303\\pleiades\\workspace\\seminar final\\semina2.xlsx");

        Workbook book = null;
        try {
	    // 今回、WorkBookはWorkbookFactoryを使って作成します
	    book = WorkbookFactory.create(in);

	} catch (EncryptedDocumentException e1) {
	    e1.printStackTrace();

	} catch (InvalidFormatException e1) {
   	    e1.printStackTrace();
	}

	// 「サンプル」という名前のシートを取得
	Sheet sheet = book.getSheet("Sheet1");

	// 1行目取得 ※Excel上、行番号は1からスタートしてますが、
	// ソース内では0からのスタートになっているので要注意！
	Row row = CellUtil.getRow(time_int+1,sheet);

	// 1つ目のセルを取得 ※行と同じく、0からスタート
	Cell cell = CellUtil.getCell(row,i);    // Excel上、「A1」の場所

	// 値をセット
	cell.setCellValue(name);

	Row row2=CellUtil.getRow(time_int+6,sheet);

	Cell cell2=CellUtil.getCell(row2,i);

	cell2.setCellValue(Numberofpeople);

	Row row3=CellUtil.getRow(time_int+11,sheet);

	Cell cell3=CellUtil.getCell(row3,i);

	cell3.setCellValue(id);

	// ここから出力処理
	FileOutputStream out = null;
	try {
	    // 出力先のファイルを指定
	    out = new FileOutputStream("C:\\Users\\74160\\Desktop\\pleiades-e4.2-java-jre_20130303\\pleiades\\workspace\\seminar final\\semina2.xlsx");
	    // 上記で作成したブックを出力先に書き込み
	    book.write(out);

	} catch (FileNotFoundException e) {
	    System.out.println(e.getStackTrace());

	} finally {
	    // 最後はちゃんと閉じておきます

	    book.close();
	}
    }
}