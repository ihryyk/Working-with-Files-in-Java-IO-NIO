package task3;

public enum MoverVersion {
  FILE_STREAMS(new FileStreamsMover(), "FileStreams"),
  FILE_STREAMS_BUFFER(new FileStreamsWithBufferMover(), "FileStreams with buffer 100kb"),
  FILE_CHANNEL(new FileChannelMover(), "FileChannel"),
  NIO(new FileNioMover(), "NIO 2 File API");

  private final FastFileMover mover;
  private final String description;

  MoverVersion(FastFileMover mover, String description) {
    this.mover = mover;
    this.description = description;
  }

  public FastFileMover getMover() {
    return this.mover;
  }

  @Override
  public String toString() {
    return this.description;
  }

}
