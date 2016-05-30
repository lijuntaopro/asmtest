package lijuntao.asmtest.loader;

import java.io.File;
import java.io.FileInputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.net.URLStreamHandler;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

public class WebAppClassLoader extends URLClassLoader{
	private static Logger log = Logger.getLogger("WebAppClassLoader");
	private static final String[] triggers = {"javax.servlet.Servlet"};
	private static final String[] packageTriggers = {
		"javax", // Java extensions
		"java",
		"org.xml.sax", // SAX 1 & 2
		"org.w3c.dom", // DOM 1 & 2
		"org.apache.xerces", // Xerces 1 & 2
		"org.apache.xalan" // Xalan
	};
	private Map<String,ResourceEntry> resourceEntries = new HashMap<String,ResourceEntry>();
	private Map<String,ResourceEntry> notFoundResources = new HashMap<String,ResourceEntry>();
	private static URL[] urls;
	static{
		System.out.println("asdasdadsadasd");
		String property = System.getProperty("user.dir");
		String path = property + File.separator + "WebRoot/WEB-INF/classes/";
		String path2 = property + File.separator + "WebRoot/WEB-INF/lib/";
		String path3 = property + File.separator + "webapp/WEB-INF/classes/";
		String path4 = property + File.separator + "webapp/WEB-INF/lib/";
		String[] name = getJarName(path4);
		urls = new URL[3+name.length];
		
		try {
			urls[0] = new URL("file",null,path);
			urls[1] = new URL("file",null,path2);
			urls[2] = new URL("file",null,path3);
			for(int i=0;i<name.length;i++){
				urls[3+i] = new URL("file",null,path4+name[i]);
			}
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		System.out.println("urls:"+Arrays.toString(urls));
	}
	public static String[] getJarName(String direction){
		File file = new File(direction);
		boolean isdirect = file.isDirectory();
		String[] list = new String[0];
		System.out.println("webappclassLoader in");
		if(isdirect){
			System.out.println(file.getAbsolutePath());
			list = file.list();
			System.out.println(Arrays.toString(list));
		}else
			log.info("这个jar仓库不存在");
		return list;
	}
	public WebAppClassLoader(ClassLoader parent) {
		super(urls,parent);
		log.info("调用super(urls,parent);");
	}
	public WebAppClassLoader(){
		super(urls);
		log.info("调用super(urls)");
	}
	public void addRepository(String repository) {
		URLStreamHandler streamHandler = null;
		try {
			URL url = new URL(null, repository, streamHandler);
			addURL(url);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}	
	}
	public String[] findRepositories() {
		URL[] urls = getURLs();
		if(urls==null)
			return null;
		String[] strings = new String[urls.length];
		for(int i=0;i<urls.length;i++){
			strings[i] = urls[i].toString();
		}
		return strings;
	}
	public boolean modified() {
		// TODO Auto-generated method stub
		return false;
	}
	
	public final synchronized Class loadClass(String className) throws ClassNotFoundException{
		//1、先从web类加载类的自身缓存得到源
		ResourceEntry entry = resourceEntries.get(className);
		log.info("开始加载。即将加载的类名:"+className);
		if(entry==null){
		//2、web类加载类的缓存没有源，在classLoader获取源
			log.info(className+"在webappclassloader类加载器没有缓存");
			Class<?> class1 = findLoadedClass(className);
			if(class1!=null){
				log.info("本地缓存存在该类："+class1.getName()+";类加载器为："+class1.getClassLoader());
				log.info("本地缓存存在该类：重新加载"+class1.getName());
				return reload(className);
			}else{
		//3、使用安全管理器来判断是否能类加载器能否加载。
				try {
					SecurityManager security = System.getSecurityManager();
					if (security != null) {
					    security.checkCreateClassLoader();
					}
				} catch (Exception e) {
					throw new ClassNotFoundException("不能加载类："+className);
				}
			}
			
        //4、加载的类是被排除特定的类、特定的或者使用委托机制的类
			if(checkClassNameBelongToClass(className,triggers)||checkClassNameBelongToPackage(className,packageTriggers)){
				try{
					log.info("加载的类是被排除特定的类、包下的类或者使用委托机制的类，由其父类加载器加载："+className);
					ClassLoader parent2 = getParent();
					if(parent2==null){
						log.info("父类为空，由系统类加载器加载："+className);
						parent2 = getSystemClassLoader();
					}else{
						log.info("父类不为空，由父类加载器加载："+className);
					}
					return parent2.loadClass(className);
				}catch(Exception e){
					log.info("父类加载器没法加载该类："+className);
				}
			}
			
			
		//5、最后，自己加载类，用继承URLClassLoader的findClass来在webAppClassLoader的类库中加载类
			log.info("属于自己的类库的类或者父类无法加载，在自己的类库中加载类："+className);
			Class<?> class2 = null;
			try{
				class2 = findClass(className);
			}catch(Exception e){
				log.info("在自己的类库中无法加载类"+className+"error");
				log.info("虽然，不属于父类加载器加载："+className);
				log.info("父类加载器尝试加载："+className);
				ClassLoader parent2 = getParent();
				if(parent2==null){
					log.info("父类为空，由系统类加载器加载："+className);
					parent2 = getSystemClassLoader();
				}else{
					log.info("父类不为空，由父类加载器加载："+className);
				}
				class2 =  parent2.loadClass(className);
			}
			return class2;
		}else{
		//7、有自身缓存，直接得到
			log.info("在web加载类的源中找到，直接返回已加载的类");
			Class class1 = entry.loadedClass;
			ResourceEntry entry2 = new ResourceEntry();
			entry2.loadedClass = class1;
			resourceEntries.put(className, entry2);
			return class1;
		}
//		String[] strings = findRepositories();
//		for(String s:strings){
//			System.out.println("reposity:"+s);
//		}
//		Package[] packages2 = getPackages();
//		for(Package p:packages2){
//			System.out.println("p.getName():"+p.getName());
//		}
		//都没有源，则查找web类加载类的加载路径，是否存在该类
//			URL url = findResource(className);
//			if(url!=null){
//				log.info("url:"+url.toString());
//			}else{
//				log.info("找不到url");
//			}
//			String fileStr = urls[0].toString().substring(5)+className.replace('.', '/')+".class";
//			System.out.println("fileStr:"+fileStr);
		//加载该class
//			if(fileStr!=null){
//				File file = new File(fileStr);
//				if(file.exists()){
//					try {
//						//如果存在类文件，则加载
//						log.info("找到文件");
//						FileInputStream fis = new FileInputStream(file);
//						int i = fis.available();
//						byte[] bs = new byte[i];
//						fis.read(bs);
//						ResourceEntry entry2 = new ResourceEntry();
//						Class<?> class2 = defineClass(className,bs,0,i);
//						entry2.binaryContent = bs;
//						entry2.CodeBase = url;
//						entry2.source = url;
//						entry2.loadedClass = class2;
//						resourceEntries.put(className, entry2);
//						return class2;
//					} catch (Exception e) {
//						throw new ClassNotFoundException();
//					}
//				}else
//					log.info("在url库找不到指定的类。");
//			}
	}
	
	public boolean checkClassNameBelongToPackage(String className,String[] packages){
		for(String s:packages){
			if(className.startsWith(s)){
				int clen = className.length();
				int slen = s.length();
				if(clen>slen&&className.charAt(slen)=='.')
					return true;
			}
		}
		return false;
	}
	public boolean checkClassNameBelongToClass(String className,String[] classes){
		for(String s:classes){
			if(s.equals(className))
				return true;
		}
		return false;
	}
	
	public Class reload(String className) throws ClassNotFoundException{
		return new WebAppClassLoader(getParent()).findClass(className);
	}
	
	public Class defineClass(String name,byte[] bs){
		return defineClass(name, bs, 0, bs.length);
	}
}

