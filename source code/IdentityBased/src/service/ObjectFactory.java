package service;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;

/**
 * This object contains factory methods for each Java content interface and Java
 * element interface generated in the service package.
 * <p>
 * An ObjectFactory allows you to programatically construct new instances of the
 * Java representation for XML content. The Java representation of XML content
 * can consist of schema derived interfaces and classes representing the binding
 * of schema type definitions, element declarations and model groups. Factory
 * methods for each of these are provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

	private final static QName _FetchFileResponse_QNAME = new QName(
			"http://logic.com/", "fetchFileResponse");
	private final static QName _SaveFile_QNAME = new QName("http://logic.com/",
			"saveFile");
	private final static QName _FetchFilesResponse_QNAME = new QName(
			"http://logic.com/", "fetchFilesResponse");
	private final static QName _DeleteUsrFileResponse_QNAME = new QName(
			"http://logic.com/", "deleteUsrFileResponse");
	private final static QName _FetchFile_QNAME = new QName(
			"http://logic.com/", "fetchFile");
	private final static QName _FetchFiles_QNAME = new QName(
			"http://logic.com/", "fetchFiles");
	private final static QName _DeleteUsrFile_QNAME = new QName(
			"http://logic.com/", "deleteUsrFile");
	private final static QName _RwPackets_QNAME = new QName(
			"http://logic.com/", "RwPackets");
	private final static QName _SaveFileResponse_QNAME = new QName(
			"http://logic.com/", "saveFileResponse");
	private final static QName _Upload_QNAME = new QName("http://logic.com/",
			"upload");
	private final static QName _RwPacketsResponse_QNAME = new QName(
			"http://logic.com/", "RwPacketsResponse");
	private final static QName _UploadResponse_QNAME = new QName(
			"http://logic.com/", "uploadResponse");

	/**
	 * Create a new ObjectFactory that can be used to create new instances of
	 * schema derived classes for package: service
	 * 
	 */
	public ObjectFactory() {
	}

	/**
	 * Create an instance of {@link UploadResponse }
	 * 
	 */
	public UploadResponse createUploadResponse() {
		return new UploadResponse();
	}

	/**
	 * Create an instance of {@link FetchFilesResponse }
	 * 
	 */
	public FetchFilesResponse createFetchFilesResponse() {
		return new FetchFilesResponse();
	}

	/**
	 * Create an instance of {@link DeleteUsrFileResponse }
	 * 
	 */
	public DeleteUsrFileResponse createDeleteUsrFileResponse() {
		return new DeleteUsrFileResponse();
	}

	/**
	 * Create an instance of {@link RwPackets }
	 * 
	 */
	public RwPackets createRwPackets() {
		return new RwPackets();
	}

	/**
	 * Create an instance of {@link FetchFileResponse }
	 * 
	 */
	public FetchFileResponse createFetchFileResponse() {
		return new FetchFileResponse();
	}

	/**
	 * Create an instance of {@link SaveFile }
	 * 
	 */
	public SaveFile createSaveFile() {
		return new SaveFile();
	}

	/**
	 * Create an instance of {@link FetchFile }
	 * 
	 */
	public FetchFile createFetchFile() {
		return new FetchFile();
	}

	/**
	 * Create an instance of {@link RwPacketsResponse }
	 * 
	 */
	public RwPacketsResponse createRwPacketsResponse() {
		return new RwPacketsResponse();
	}

	/**
	 * Create an instance of {@link SaveFileResponse }
	 * 
	 */
	public SaveFileResponse createSaveFileResponse() {
		return new SaveFileResponse();
	}

	/**
	 * Create an instance of {@link FetchFiles }
	 * 
	 */
	public FetchFiles createFetchFiles() {
		return new FetchFiles();
	}

	/**
	 * Create an instance of {@link DeleteUsrFile }
	 * 
	 */
	public DeleteUsrFile createDeleteUsrFile() {
		return new DeleteUsrFile();
	}

	/**
	 * Create an instance of {@link Upload }
	 * 
	 */
	public Upload createUpload() {
		return new Upload();
	}

	/**
	 * Create an instance of {@link JAXBElement }{@code <}
	 * {@link FetchFileResponse }{@code >}
	 * 
	 */
	@XmlElementDecl(namespace = "http://logic.com/", name = "fetchFileResponse")
	public JAXBElement<FetchFileResponse> createFetchFileResponse(
			FetchFileResponse value) {
		return new JAXBElement<FetchFileResponse>(_FetchFileResponse_QNAME,
				FetchFileResponse.class, null, value);
	}

	/**
	 * Create an instance of {@link JAXBElement }{@code <}{@link SaveFile }{@code
	 * >}
	 * 
	 */
	@XmlElementDecl(namespace = "http://logic.com/", name = "saveFile")
	public JAXBElement<SaveFile> createSaveFile(SaveFile value) {
		return new JAXBElement<SaveFile>(_SaveFile_QNAME, SaveFile.class, null,
				value);
	}

	/**
	 * Create an instance of {@link JAXBElement }{@code <}
	 * {@link FetchFilesResponse }{@code >}
	 * 
	 */
	@XmlElementDecl(namespace = "http://logic.com/", name = "fetchFilesResponse")
	public JAXBElement<FetchFilesResponse> createFetchFilesResponse(
			FetchFilesResponse value) {
		return new JAXBElement<FetchFilesResponse>(_FetchFilesResponse_QNAME,
				FetchFilesResponse.class, null, value);
	}

	/**
	 * Create an instance of {@link JAXBElement }{@code <}
	 * {@link DeleteUsrFileResponse }{@code >}
	 * 
	 */
	@XmlElementDecl(namespace = "http://logic.com/", name = "deleteUsrFileResponse")
	public JAXBElement<DeleteUsrFileResponse> createDeleteUsrFileResponse(
			DeleteUsrFileResponse value) {
		return new JAXBElement<DeleteUsrFileResponse>(
				_DeleteUsrFileResponse_QNAME, DeleteUsrFileResponse.class,
				null, value);
	}

	/**
	 * Create an instance of {@link JAXBElement }{@code <}{@link FetchFile }
	 * {@code >}
	 * 
	 */
	@XmlElementDecl(namespace = "http://logic.com/", name = "fetchFile")
	public JAXBElement<FetchFile> createFetchFile(FetchFile value) {
		return new JAXBElement<FetchFile>(_FetchFile_QNAME, FetchFile.class,
				null, value);
	}

	/**
	 * Create an instance of {@link JAXBElement }{@code <}{@link FetchFiles }
	 * {@code >}
	 * 
	 */
	@XmlElementDecl(namespace = "http://logic.com/", name = "fetchFiles")
	public JAXBElement<FetchFiles> createFetchFiles(FetchFiles value) {
		return new JAXBElement<FetchFiles>(_FetchFiles_QNAME, FetchFiles.class,
				null, value);
	}

	/**
	 * Create an instance of {@link JAXBElement }{@code <}{@link DeleteUsrFile }
	 * {@code >}
	 * 
	 */
	@XmlElementDecl(namespace = "http://logic.com/", name = "deleteUsrFile")
	public JAXBElement<DeleteUsrFile> createDeleteUsrFile(DeleteUsrFile value) {
		return new JAXBElement<DeleteUsrFile>(_DeleteUsrFile_QNAME,
				DeleteUsrFile.class, null, value);
	}

	/**
	 * Create an instance of {@link JAXBElement }{@code <}{@link RwPackets }
	 * {@code >}
	 * 
	 */
	@XmlElementDecl(namespace = "http://logic.com/", name = "RwPackets")
	public JAXBElement<RwPackets> createRwPackets(RwPackets value) {
		return new JAXBElement<RwPackets>(_RwPackets_QNAME, RwPackets.class,
				null, value);
	}

	/**
	 * Create an instance of {@link JAXBElement }{@code <}
	 * {@link SaveFileResponse }{@code >}
	 * 
	 */
	@XmlElementDecl(namespace = "http://logic.com/", name = "saveFileResponse")
	public JAXBElement<SaveFileResponse> createSaveFileResponse(
			SaveFileResponse value) {
		return new JAXBElement<SaveFileResponse>(_SaveFileResponse_QNAME,
				SaveFileResponse.class, null, value);
	}

	/**
	 * Create an instance of {@link JAXBElement }{@code <}{@link Upload }{@code >}
	 * 
	 */
	@XmlElementDecl(namespace = "http://logic.com/", name = "upload")
	public JAXBElement<Upload> createUpload(Upload value) {
		return new JAXBElement<Upload>(_Upload_QNAME, Upload.class, null, value);
	}

	/**
	 * Create an instance of {@link JAXBElement }{@code <}
	 * {@link RwPacketsResponse }{@code >}
	 * 
	 */
	@XmlElementDecl(namespace = "http://logic.com/", name = "RwPacketsResponse")
	public JAXBElement<RwPacketsResponse> createRwPacketsResponse(
			RwPacketsResponse value) {
		return new JAXBElement<RwPacketsResponse>(_RwPacketsResponse_QNAME,
				RwPacketsResponse.class, null, value);
	}

	/**
	 * Create an instance of {@link JAXBElement }{@code <}{@link UploadResponse }
	 * {@code >}
	 * 
	 */
	@XmlElementDecl(namespace = "http://logic.com/", name = "uploadResponse")
	public JAXBElement<UploadResponse> createUploadResponse(UploadResponse value) {
		return new JAXBElement<UploadResponse>(_UploadResponse_QNAME,
				UploadResponse.class, null, value);
	}

}
