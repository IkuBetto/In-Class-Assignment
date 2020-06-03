package kai;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.ss.util.CellUtil;

public class excel_yomikomi_UPDATE {



	public String yomikomi(String index,String index2) throws IOException {
    	int i = Integer.parseInt(index);
    	int t = Integer.parseInt(index2);
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
			Row row = CellUtil.getRow(t+11,sheet);

			// 1つ目のセルを取得 ※行と同じく、0からスタート
			Cell cell = CellUtil.getCell(row,i);

			// 値をセット

			String cell_str=cell.getStringCellValue();
			book.close();

			return cell_str;

    }

}