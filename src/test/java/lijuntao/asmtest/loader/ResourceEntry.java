package lijuntao.asmtest.loader;
import java.net.URL;
import java.security.cert.Certificate;
import java.util.jar.Manifest;

public class ResourceEntry {
	public long lastModidled = -1;
	public byte[] binaryContent = null;
	public Class loadedClass = null;
	public URL source = null;
	public URL CodeBase = null;
	public Manifest manifest = null;
	public Certificate[] certificates = null;
}
